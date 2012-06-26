/*
 * Copyright (C) 2012 Michael Dirska <dl1bff@mdx.de>
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package up4dar_configurator;

import java.io.IOException;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Michael Dirska <dl1bff@mdx.de>
 */
public class UP4DAR_SNMP
{
    DatagramSocket socket;
    InetAddress addr;
    
    UP4DAR_SNMP(InetAddress addr)
    {
        this.addr = addr;
        socket = null;
    }

  
    static char getOIDChar(int i)
    {
        if (i < 10)
        {
            return (char) (48 + i);
        }
        
        return (char) (65 + i - 10); 
    }
   
    
    enum SNMP_DataType { OctetString, Integer };
    
    enum SNMP_ReqType { GetRequest, SetRequest };
    
    
    static final int BER_INTEGER = 0x02;
    static final int BER_OCTETSTRING = 0x04;
    static final int BER_NULL =	0x05;
    static final int BER_OID = 0x06;
    static final int BER_SEQUENCE = 0x30;
    
    static final int BER_SNMP_GET = 0xA0;
    static final int BER_SNMP_GETNEXT = 0xA1;
    static final int BER_SNMP_RESPONSE = 0xA2;
    static final int BER_SNMP_SET = 0xA3;
    
    static int request_id = 0;
    
    static final byte[] up4dar_oid = { 0x2b, 0x06, 0x01, 0x03, (byte) 0xab, 0x45, 0x01 };
    
    
    int get_oid_len(String oid)
    {
        return up4dar_oid.length + oid.length();
    }
    
    void oid_copy (byte[] binData, byte[] oidBytes, int pointer, String oid )
    {
        int i;
        
        for (i=0; i < up4dar_oid.length; i++)
        {
            binData[pointer + i] = up4dar_oid[i];
            oidBytes[i] = up4dar_oid[i];
        }
        
        for (i=0; i < oid.length(); i++)
        {
            char c = oid.charAt(i);
            byte d;
            
            if ((c >= '0') && (c <= '9')) // numbers
            {
               d = (byte) ( c & 0x0F );
            }
            else  // letters
            {
               d = (byte) (( c & 0x1F ) + 9);
            }
            
            binData[pointer + i + up4dar_oid.length] = d;
            oidBytes[i + up4dar_oid.length] = d;
        }
    }
    
    class SNMP_Request
    {
        
        final int reqId;
        final SNMP_DataType dataType;
        final SNMP_ReqType reqType;
        final int udpPacketLen;
        
        byte[] binData;
        byte[] oidBytes;

        public SNMP_Request( SNMP_DataType dataType, SNMP_ReqType reqType,
                String oid, String communityString, byte[] setData)
        {
            request_id ++;
            
            reqId = request_id;
            
            this.reqType = reqType;
            this.dataType = dataType;
            
            binData = new byte[200];
            oidBytes = new byte[get_oid_len(oid)];
                        
            byte cmnty[] = communityString.getBytes();
            int community_string_len = cmnty.length;
            
            if (community_string_len >= 127)
            {
                community_string_len = 126;
            }
            
            
            int tmp_len = 0;
	
            
            // request for exactly one parameter
            
                    tmp_len += get_oid_len(oid);
                    if (reqType == SNMP_ReqType.SetRequest)
                    {
                        tmp_len += setData.length;
                        tmp_len += 4; // type and length for value
                    } else
                    {
                        tmp_len += 2; // type and length for value (null value)
                    }
                  
                    tmp_len += 2; // type and length for oid
                    tmp_len += 4; // varbind sequence (2 byte length field)

            tmp_len += 4; // varbind list sequence (2 byte length field)
            tmp_len += 3; // error index
            tmp_len += 3; // error
            tmp_len += 6; // request_id ( 4 byte integer )
            tmp_len += 4; // getResponse (2 byte length field)
            tmp_len += 2 + community_string_len; // communitystring, length < 127
            tmp_len += 3; // snmp_version
	
	    udpPacketLen =  tmp_len + 4; // the UDP packet length
            
            if (udpPacketLen > binData.length)
            {
                // TODO: error handling
            }
            
            int p = 0;
            
            binData[p + 0] = BER_SEQUENCE;
            binData[p + 1] = (byte) 0x82;
            binData[p + 2] = (byte) ((tmp_len >> 8) & 0xFF);
            binData[p + 3] = (byte) (tmp_len & 0xFF);
	
            p += 4;
	
            binData[p + 0] = BER_INTEGER;
            binData[p + 1] = 1;
            binData[p + 2] = 0;  // version = SNMPv1

            p += 3;

            
            
            binData[p + 0] = BER_OCTETSTRING;
            binData[p + 1] = (byte) (community_string_len);
            
            int i;
            for (i=0; i < community_string_len; i++)
            {
                binData[p + 2 + i] = cmnty[i]; // copy string
            }
            
            p += 2 + community_string_len;
	
            tmp_len -= 3 + 2 + community_string_len + 4;
            
            if (reqType == SNMP_ReqType.SetRequest)
            {
                binData[p + 0] = (byte) BER_SNMP_SET;
            } else
            {
                binData[p + 0] = (byte) BER_SNMP_GET;
            }
            binData[p + 1] = (byte) 0x82;
            binData[p + 2] = (byte) ((tmp_len >> 8) & 0xFF);
            binData[p + 3] = (byte) (tmp_len & 0xFF);
	
            p += 4;
	
            binData[p + 0] = BER_INTEGER;
            binData[p + 1] = 4;
            binData[p + 2] = (byte) ((reqId >> 24) & 0xFF);
            binData[p + 3] = (byte) ((reqId >> 16) & 0xFF);
            binData[p + 4] = (byte) ((reqId >> 8) & 0xFF);
            binData[p + 5] = (byte) (reqId & 0xFF);
	
            p += 6;

            binData[p + 0] = BER_INTEGER;
            binData[p + 1] = 1;
            binData[p + 2] = 0;  // error = 0
	
            p += 3;
	
            binData[p + 0] = BER_INTEGER;
            binData[p + 1] = 1;
            binData[p + 2] = 0;  // error index = 0
	
            p += 3;
	
            tmp_len -= 6 + 3 + 3 + 4;
	
            binData[p + 0] = BER_SEQUENCE;
            binData[p + 1] = (byte) 0x82;
            binData[p + 2] = (byte) ((tmp_len >> 8) & 0xFF);
            binData[p + 3] = (byte) (tmp_len & 0xFF);
	
            p += 4;
	
            // one parameter
                int oid_len = get_oid_len(oid);

                int t;
                
                if (reqType == SNMP_ReqType.SetRequest)
                {
                    t = setData.length;
                    t += 4; // type and length for value (2 byte length field)
                    
                } else
                {
                    t = 2; // // type and length for value (null value)
                }
                
                t += oid_len; // oid length
                t += 2; // type and length for oid


                binData[p + 0] = BER_SEQUENCE;
                binData[p + 1] = (byte) 0x82;
                binData[p + 2] = (byte) ((t >> 8) & 0xFF);
                binData[p + 3] = (byte) (t & 0xFF);

                p += 4;

                binData[p + 0] = (byte) BER_OID;
                binData[p + 1] = (byte) oid_len;

                oid_copy(binData, oidBytes, p + 2, oid);
             

                p += 2 + oid_len;

                if (reqType == SNMP_ReqType.SetRequest)                             
                {
                    
                    if (dataType == SNMP_DataType.Integer)
                    {
                        binData[p + 0] = BER_INTEGER;
                    }
                    else
                    {
                        binData[p + 0] = BER_OCTETSTRING;
                    }

                    binData[p + 1] = (byte) 0x82;
                    binData[p + 2] = (byte) ((setData.length >> 8) & 0xFF);
                    binData[p + 3] = (byte) (setData.length & 0xFF);
                  

                    for (i=0; i < setData.length; i++)
                    {
                        binData[p + 4 + i] = setData[i]; // copy data
                    }

                } else
                {
                    binData[p + 0] = BER_NULL;
                    binData[p + 1] = 0;
                }
        }
        
    }
        
    public String snmpConnect() throws Exception
    {
        try
        {
            socket = new DatagramSocket();
            socket.setSoTimeout(200); // 200ms
            
        } catch (SocketException ex)
        {
            Logger.getLogger(UP4DAR_SNMP.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
        return snmpGetString("110");
    }
    
    class SNMP_Response
    {
        private SNMP_DataType datatype;
        
        int value;
        
        byte[] octetstring; 

        public SNMP_Response()
        {
            datatype = SNMP_DataType.Integer;
            value = 0;
        }
        
        public SNMP_Response( int value )
        {
            datatype = SNMP_DataType.Integer;
            this.value = value;
        }
         
        public SNMP_Response( byte[] octetstring )
        {
             datatype = SNMP_DataType.OctetString;
             this.octetstring = octetstring;
        }

        private SNMP_DataType getDataType()
        {
            return datatype;
        }
        
        int getIntValue()
        {
            return value;
        }
        
        byte[] getData()
        {
            return octetstring;
        }
    }
    
    class SNMP_Parser
    {
        byte[] data;
        int pointer;
        int data_remaining;
        
        SNMP_Parser(DatagramPacket dp)
        {
            data = new byte[dp.getLength()];
            
            int i;
            
            for (i=0; i < dp.getLength(); i++)
            {
                data[i] = dp.getData()[i];
            }
            
            pointer = 0;
            data_remaining = data.length;
        }
        
        int getOctet (int ptr)
        {
            byte b = data[ptr + pointer];
            
            if ( b < 0 )
            {
                return 256 + b;
            }
            else
            {
                return b;
            }
        }
        
        int len_len; 
        
        int get_length( )
        {
            int len = getOctet(1);            
            len_len = 1;

            if (len > 0x7F)
            {
                switch(getOctet(1) & 0x7F)
                {
                case 1:
                        len = getOctet(2);
                        len_len = 2;
                        break;
                case 2:
                        len = (getOctet(2) << 8) | getOctet(3);
                        len_len = 3;
                        break;
                default:  // length field much to long
                        len = -1; // error code
                        break;
                }
            }

            return len;
        }

        
        int check_type(int datatype)
        {
            if (datatype != getOctet(0))  // type not correct
                    return 1; 
		                   	
            int len = get_length( ); 
	
            if (len < 0)   // invalid length field
                    return 1;

                 
            if ((len + 1 + len_len ) > data_remaining)  // length incorrect
                    return 1;

            return 0;            
        }
        
        int value;

        int get_integer()
        {
            if (BER_INTEGER != getOctet(0))  // type not correct
                    return 1; 

            int len = get_length( ); 

            if (len < 0)   // invalid length field
                    return 1;

            if ((len + 1 + len_len) > data_remaining)  // length incorrect
                    return 1;	

            value = 0;

            if ((getOctet( 1 + len_len  ) & 0x80) != 0) // sign bit
            {
                value = -1;
            }

            int i;
            
            for (i=0; i < len; i++)
            {
                value = ((value) << 8) | getOctet( 1 + i + len_len  );
            }

            return 0;
        }
        
        int result_len;
        byte[] result_bytes;

        int get_octetstring(int valType, int maxlen)
        {
            if (getOctet(0) != valType)  //  type not correct
                    return 1;

            int len = get_length( );

            if (len < 0)   // invalid length field
                    return 1;

            if ((len + 1 + len_len) > data_remaining)  // length incorrect
                    return 1;	

            if (len > maxlen)
                    return 1;  // too long

            int i;

            for (i=0; i < len; i++)
            {
                result_bytes[i] = data[pointer + i + len_len + 1];
            }

            result_len = len;

            return 0;
        }


        void enter_sequence()
        {             
            int len = get_length( ); 

            if (len < 0)   // invalid length field
                    return;

            pointer += 1 + len_len;
            data_remaining -= 1 + len_len;
        }

        void ber_skip()
        {
            int len = get_length( ); 

            if (len < 0)   // invalid length field
                    return;

            pointer += 1 + len_len + len;
            data_remaining -=  1 + len_len + len;  
        }

    }
    
    SNMP_Response parseReply(SNMP_Request req, DatagramPacket dp)
    {
        // System.err.println(dp.getLength());
        
        SNMP_Parser p = new SNMP_Parser(dp);
        
        if (p.check_type(BER_SEQUENCE) != 0)  return null; // first element must be SEQUENCE
	
	p.enter_sequence();
	
	if (p.get_integer() != 0)  return null; // snmp version
	
	if (p.value != 0)  return null; // 0 == SMNPv1
	
	p.ber_skip();
        
        p.result_bytes = new byte[100];
	
	if (p.get_octetstring(BER_OCTETSTRING,
	      p.result_bytes.length ) != 0)  return null; // snmp community
	
	p.ber_skip();
	
        
        
			
	if (p.check_type(BER_SNMP_RESPONSE) != 0)  return null;
	
	p.enter_sequence();
	
	if (p.get_integer() != 0)  return null; // get Request-ID
        
        if (p.value != req.reqId) return null; // ID does not match
	
	p.ber_skip();
	
	if (p.check_type(BER_INTEGER) != 0)  return null; // error
	
        if (p.get_integer() != 0)  return null; // get error code
        
	if (p.value != 0) return null;  // error code != 0
       	
	p.ber_skip();
	
	if (p.check_type(BER_INTEGER) != 0)  return null; // error index
	
	p.ber_skip();
	
	if (p.check_type(BER_SEQUENCE) != 0)  return null; // varbind list
	
	p.enter_sequence();
        
       
	
	// read exactly one varbind
        
		if (p.check_type(BER_SEQUENCE) != 0)  return null; // varbind
		
		p.enter_sequence();
		
		p.result_bytes = new byte[req.oidBytes.length];
		
		if (p.get_octetstring(BER_OID, req.oidBytes.length) != 0)  return null; // oid
		
                if (p.result_len != req.oidBytes.length) return null; // length not the same
                
               
                
                int i;
                for (i=0; i < req.oidBytes.length; i++)
                {
                    //System.err.println("OID " + p.result_bytes[i] + " " + req.oidBytes[i]);
                    if (p.result_bytes[i] != req.oidBytes[i]) return null; // oid mismatch
                }
		
		p.ber_skip();
		
                  // System.err.println("X");
                
		if (req.reqType == SNMP_ReqType.SetRequest)
                {
                    return new SNMP_Response();
                }
                else
                {
                    if (req.dataType == SNMP_DataType.Integer)
                    {
                        if (p.check_type(BER_INTEGER) != 0)  return null; // check for integer
                        
                        if (p.get_integer() != 0)  return null; // get integer
                        
                        return new SNMP_Response( p.value );
                    }
                    else
                    {
                        if (p.check_type(BER_OCTETSTRING) != 0)  return null; // check for string
                        
                        int len = p.get_length();
                        
                        if (len < 0)  return null; // length problem
                        
                        p.result_bytes = new byte[len];
                        
                        if (p.get_octetstring(BER_OCTETSTRING, len) != 0)  return null;
                        
                        return new SNMP_Response( p.result_bytes );
                    }
                }
              	
       
    }
    
    
    SNMP_Response sendAndRecv(SNMP_Request req)
    {
        DatagramPacket dp = new DatagramPacket(req.binData, req.udpPacketLen, addr, 161);        
        byte[] recvBuf = new byte[200];
        DatagramPacket drx = new DatagramPacket(recvBuf, recvBuf.length);
        
        int retryCounter = 3;
        
        while (retryCounter > 0)
        {
            try
            {
                socket.send(dp);
                
            } catch (IOException ex)
            {
                Logger.getLogger(UP4DAR_SNMP.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
            
            try
            {
                socket.receive(drx);
                
                return parseReply(req, drx);
                
            } catch (SocketTimeoutException ex)
            {
                // retry
                //System.out.println("Retry: " + retryCounter);
            }
            catch (IOException ex)
            {
                Logger.getLogger(UP4DAR_SNMP.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
            
            retryCounter --;
        }
        
        return null; // retryCounter == 0
    }
    
    final static String defaultCommunityString = "public";
    
    public byte[] snmpGetBinaryString(String oid)
            throws Exception
    {   
        SNMP_Request req = new SNMP_Request( SNMP_DataType.OctetString,
                SNMP_ReqType.GetRequest,  oid, defaultCommunityString, null );
        
        SNMP_Response res =  sendAndRecv(req);
        
        if (res != null)
        {
            // System.out.println("!!!");
            
            if (res.getDataType() == SNMP_DataType.OctetString)
            {
                return res.getData();
            }
        }
 
        throw new Exception("getString failed, oid=" + oid);
    }
    
    public String snmpGetString(String oid)
            throws Exception
    {   
        return new String(snmpGetBinaryString(oid));
    }
    
    
    void snmpSetString(String oid, String data) throws Exception   
    {
        
        byte[] dataBytes = data.getBytes();
        
        SNMP_Request req = new SNMP_Request( SNMP_DataType.OctetString,
                SNMP_ReqType.SetRequest,  oid, defaultCommunityString,
                    dataBytes);
        
        SNMP_Response res =  sendAndRecv(req);
        
        if (res == null)        
            throw new Exception("setString failed, oid=" + oid + " data=" + data);
    }
    
    public int snmpGetInteger(String oid)
            throws Exception
    {   
        SNMP_Request req = new SNMP_Request( SNMP_DataType.Integer,
                SNMP_ReqType.GetRequest,  oid, defaultCommunityString, null );
        
        SNMP_Response res =  sendAndRecv(req);
        
        if (res != null)
        {
            
            if (res.getDataType() == SNMP_DataType.Integer )
            {
                return res.getIntValue();
            }
        }
 
        throw new Exception("getInteger failed, oid=" + oid); 
    }
    
    void snmpSetInteger(String oid, int value) throws Exception
    {
        byte[] dataBytes = new byte[4];
        
        int i;
                
        for (i=0; i < 4; i++)
        {
            dataBytes[i] = (byte) (( value >> (8* (3-i))) & 0xFF);
            
            // System.out.println("i="+ i + "  d="+dataBytes[i]);
        }                
        
        SNMP_Request req = new SNMP_Request( SNMP_DataType.Integer,
                SNMP_ReqType.SetRequest,  oid, defaultCommunityString,
                    dataBytes);
        
        SNMP_Response res =  sendAndRecv(req);
        
        if (res == null)        
            throw new Exception("setInteger failed, oid=" + oid + " value=" + value);
    }
}
