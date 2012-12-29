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

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractListModel;


/**
 *
 * @author Michael Dirska <dl1bff@mdx.de>
 */
public class UP4DARbroadcastRX extends AbstractListModel 
    implements Runnable
{
    class Client
    {
        public Client(InetAddress addr, String communityString)
        {
            ip = addr;
            this.communityString = communityString;
            timeToLive = 600;
            callSign = "UP4DAR-Board";
        }
        
        
        @Override
        public boolean equals(Object a)
        {
            if ( a == null || getClass() != a.getClass() )
                return false;
            
            return ip.equals(((Client) a).ip);
        }

        @Override
        public int hashCode()
        {
            int hash = 3;
            hash = 73 * hash + (this.ip != null ? this.ip.hashCode() : 0);
            return hash;
        }
        
        
        String callSign;
        int timeToLive;
        InetAddress ip;
        String communityString;
    }
    
    private final ArrayList<Client> clients;
    
    class Cleanup implements Runnable
    {
        UP4DARbroadcastRX m;
        
        Cleanup(UP4DARbroadcastRX m)
        {
            this.m = m;
        }
        
        @Override
        public void run()
        {
            while(true)
            {
                try
                {
                    Thread.sleep(1000);
                } catch (InterruptedException ex)
                {
                    Logger.getLogger(UP4DARbroadcastRX.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                boolean somethingChanged = false;
                
                synchronized (m.clients)
                {
                    int i = m.clients.size() - 1;
                    
                    while (i >= 0)
                    {
                        Client c = m.clients.get(i);
                        
                        c.timeToLive --;
                        
                        if (c.timeToLive <= 0)
                        {
                            m.clients.remove(i);
                            somethingChanged = true;
                        }
                        
                        i--;
                    }
                }
                
                if (somethingChanged)
                {
                    m.fireContentsChanged(m, 0, 0);
                }
            }
        }
    }
    
    

    public UP4DARbroadcastRX()
    {
        clients = new ArrayList<Client> ();   
    }
    
    
    public InetAddress getIP(int i)
    {
        return clients.get(i).ip;
    }
    
    public String getCmnty(int i)
    {
        return clients.get(i).communityString;
    }
    
    public void addClient( InetAddress a, String cmnty )
    {
        clients.add( new Client( a, cmnty ));
    }
    
    DatagramSocket s;
    
    public void startThreads() throws SocketException
    {
        s = new DatagramSocket(45233);
        s.setReuseAddress(true);
        
        new Thread(this).start();
        new Thread(new Cleanup(this)).start();
         
    }

    @Override
    public void run() {
        
        Thread me = Thread.currentThread( );
        me.setPriority(Thread.MIN_PRIORITY);
		
	

	try
        {
            

	    while (true)
            {	
		byte p[] = new byte[20];
		
		DatagramPacket pp = new DatagramPacket(p, p.length);

                s.receive(pp);
                
                boolean somethingChanged = false;
                
                if (pp.getLength() >= 8)
                {
                    String communityString = "public";
                    
                    if (pp.getLength() >= 20)
                    {
                        communityString = new String(p, 8, 12, "UTF-8");
                    }
		
                    synchronized (clients)
                    {
                        Client c;
                        String callSign = new String(p, 0, 8, "UTF-8");
                        Client c2 = new Client(pp.getAddress(), communityString);

                        if (clients.contains(c2))
                        {
                            c = clients.get(clients.indexOf(c2));

                            if (! c.callSign.equals(callSign))
                            {
                                somethingChanged = true;
                            }
                        }
                        else
                        {
                            c = c2;
                            clients.add(c);
                            somethingChanged = true;
                        }
                        c.callSign = callSign;
                        c.timeToLive = 10;
                    }

                    if (somethingChanged)
                    {
                        fireContentsChanged(this, 0, 0);
                    }
                
                }
	    } // while
		
	} catch (Exception ex)
	{
            Logger.getLogger(UP4DARbroadcastRX.class.getName()).log(Level.SEVERE, null, ex);
	}
    }

    
    
    @Override
    public Object getElementAt(int i) {
         Client c;
         
         synchronized (clients)
         {
             c =  clients.get(i);
         }
         
         String communityStringPresent = "";
         
         if (! c.communityString.equals("public"))
         {
             communityStringPresent = " *";
         }
                  
         return c.callSign  + " " + c.ip.toString() + 
                 communityStringPresent;
      
    }

    @Override
    public int getSize() {
        return clients.size();
    }

   
    
}
