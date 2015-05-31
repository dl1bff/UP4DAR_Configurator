/*
 * Copyright (C) 2013 Michael Dirska <dl1bff@mdx.de>
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

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingWorker;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;

/**
 *
 * @author Michael Dirska <dl1bff@mdx.de>
 */



public class UP4DAR_Configurator extends javax.swing.JFrame 
{

    public final String version = "C.1.00.10e";
    
    public String getMainWindowTitle()
    {
        return "UP4DAR Configurator - " + version;
    }
    

    private UP4DARbroadcastRX bcRX;

    /**
     * Get the value of bcRX
     *
     * @return the value of bcRX
     */
    public UP4DARbroadcastRX getBcRX() {
        return bcRX;
    }

    /**
     * Set the value of bcRX
     *
     * @param bcRX new value of bcRX
     */
    public void setBcRX(UP4DARbroadcastRX bcRX) {
        this.bcRX = bcRX;
    }

    
    UP4DAR_SNMP snmp;
    
    UP4DAR_RemoteDisplay remoteDisplay;
    
    int firmwareVersion = 0;
    
    javax.swing.Timer  remoteDisplayRefreshTimer; 
   
    /**
     * Creates new form UP4DAR_Configurator
     */
    public UP4DAR_Configurator() {
        bcRX = new UP4DARbroadcastRX();
        try
        {
            bcRX.startThreads();
            
            bcRX.addListDataListener(new ListDataListener() {
            
                @Override
                public void intervalAdded(ListDataEvent lde)
                {
                    
                }

                @Override
                public void intervalRemoved(ListDataEvent lde)
                {
                   
                }

                @Override
                public void contentsChanged(ListDataEvent lde)
                {
                    if (bcRX.getSize() == 0)
                    {
                        connectButton.setEnabled( false );
                        boardList.clearSelection();
                    }
                }
        });
            
        } catch (SocketException ex)
        {
            Logger.getLogger(UP4DAR_Configurator.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(1);
        }
        
        snmp = null;
        
        if (cmdlineIP != null)
        {
            bcRX.addClient(cmdlineIP, cmdlineCmnty);
        }
        
        remoteDisplay = new UP4DAR_RemoteDisplay(3);
        
        initComponents();
       
        remoteDisplayLabel.setIcon(new javax.swing.ImageIcon(
                       this.createImage(remoteDisplay.getImageSource()) ));
        
        remoteDisplayRefreshTimer = new javax.swing.Timer(5000,
                new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent evt)
                    {
                        
                        if (remoteDisplayLabel.isShowing() && (firmwareVersion >= 10131))
                        {
                            
                            try
                            {
                                remoteDisplay.updatePixel(snmp.snmpGetBinaryString(
                                        remoteDisplayScreens[remoteScreenComboBox.getSelectedIndex()]));
                            }
                            catch (Exception e)
                            {
                            }
                        }                       
                    }
                }
        );
        
        remoteDisplayRefreshTimer.setInitialDelay(100);
        remoteDisplayRefreshTimer.start();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        updateFileChooser = new javax.swing.JFileChooser();
        desktopPane = new javax.swing.JDesktopPane();
        networkListFrame = new javax.swing.JInternalFrame();
        boardListScrollPane = new javax.swing.JScrollPane();
        boardList = new javax.swing.JList();
        connectButton = new javax.swing.JButton();
        configFrame = new javax.swing.JInternalFrame();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        callsignPanel = new javax.swing.JPanel();
        callSign = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        callSignExt = new javax.swing.JTextField();
        dvPanel = new javax.swing.JPanel();
        rptSettingsPanel = new javax.swing.JPanel();
        rptSpinner = new javax.swing.JSpinner();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        rptTable = new javax.swing.JTable();
        rptDirectCheckbox = new javax.swing.JCheckBox();
        yourCallSettingsPanel = new javax.swing.JPanel();
        yourCallSpinner = new javax.swing.JSpinner();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        yourCallTable = new javax.swing.JTable();
        txmsgTextField = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        phyPanel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        phyTxDelay = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        phyTxGain = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        phyRxInv = new javax.swing.JCheckBox();
        phyTxDcShift = new javax.swing.JTextField();
        phySoftwareVersion = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        phyRxDevFactor = new javax.swing.JTextField();
        phySerialNumTextField = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        audioPanel = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        pttBeepFrequency = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        pttBeepDuration = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        pttBeepVolume = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        standbyBeepFrequency = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        standbyBeepDuration = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        standbyBeepVolume = new javax.swing.JTextField();
        dprsPanel = new javax.swing.JPanel();
        dprsEnableCheckBox = new javax.swing.JCheckBox();
        dprsSymbolComboBox = new javax.swing.JComboBox();
        jLabel13 = new javax.swing.JLabel();
        dprsTextField = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        displayPanel = new javax.swing.JPanel();
        backlightSlider = new javax.swing.JSlider();
        contrastSlider = new javax.swing.JSlider();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        remoteDisplayLabel = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        remoteScreenRefreshRateSlider = new javax.swing.JSlider();
        remoteScreenComboBox = new javax.swing.JComboBox();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        debugPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        inputVoltage = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        snmpCmnty = new javax.swing.JTextField();
        networkPanel = new javax.swing.JPanel();
        myIP = new up4dar_configurator.JIpTextField();
        netmaskIP = new up4dar_configurator.JIpTextField();
        gwIP = new up4dar_configurator.JIpTextField();
        DNS_IP1 = new up4dar_configurator.JIpTextField();
        DNS_IP2 = new up4dar_configurator.JIpTextField();
        NTP_IP = new up4dar_configurator.JIpTextField();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        useAltDNS = new javax.swing.JCheckBox();
        enableNTP = new javax.swing.JCheckBox();
        useOnlyTenMBit = new javax.swing.JCheckBox();
        ExitButton = new javax.swing.JButton();
        saveToFlash = new javax.swing.JButton();
        loadingFrame = new javax.swing.JInternalFrame();
        loadProgressBar = new javax.swing.JProgressBar();
        loadProgressLabel = new javax.swing.JLabel();
        loadCancelButton = new javax.swing.JButton();
        updateFrame = new javax.swing.JInternalFrame();
        updateProgressBar = new javax.swing.JProgressBar();
        updateLabel1 = new javax.swing.JLabel();
        updateOKButton = new javax.swing.JButton();
        updateLabel2 = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        openMenuItem = new javax.swing.JMenuItem();
        saveMenuItem = new javax.swing.JMenuItem();
        saveAsMenuItem = new javax.swing.JMenuItem();
        updateMenuItem = new javax.swing.JMenuItem();
        exitMenuItem = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        cutMenuItem = new javax.swing.JMenuItem();
        copyMenuItem = new javax.swing.JMenuItem();
        pasteMenuItem = new javax.swing.JMenuItem();
        deleteMenuItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        contentMenuItem = new javax.swing.JMenuItem();
        aboutMenuItem = new javax.swing.JMenuItem();

        updateFileChooser.setDialogTitle("Select Firmware File");
        updateFileChooser.setFileFilter(null);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle(getMainWindowTitle());
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);

        networkListFrame.setTitle("UP4DAR boards on the local network");
        networkListFrame.setVisible(true);

        boardListScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        boardListScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        boardList.setModel(bcRX);
        boardList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        boardList.addListSelectionListener(new javax.swing.event.ListSelectionListener()
        {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt)
            {
                boardListValueChanged(evt);
            }
        });
        boardListScrollPane.setViewportView(boardList);

        connectButton.setText("Connect");
        connectButton.setEnabled(false);
        connectButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                connectButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout networkListFrameLayout = new javax.swing.GroupLayout(networkListFrame.getContentPane());
        networkListFrame.getContentPane().setLayout(networkListFrameLayout);
        networkListFrameLayout.setHorizontalGroup(
            networkListFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(networkListFrameLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(networkListFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(boardListScrollPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, networkListFrameLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(connectButton)))
                .addContainerGap())
        );
        networkListFrameLayout.setVerticalGroup(
            networkListFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(networkListFrameLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(boardListScrollPane)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(connectButton)
                .addContainerGap())
        );

        desktopPane.add(networkListFrame);
        networkListFrame.setBounds(10, 10, 320, 214);

        configFrame.setNormalBounds(new java.awt.Rectangle(10, 15, 880, 372));
        configFrame.setPreferredSize(new java.awt.Dimension(880, 372));
        configFrame.setVisible(false);

        callSign.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        callSign.setText("NOCALL");
        callSign.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                callSignActionPerformed(evt);
            }
        });
        callSign.addFocusListener(new java.awt.event.FocusAdapter()
        {
            public void focusLost(java.awt.event.FocusEvent evt)
            {
                callSignFocusLost(evt);
            }
        });

        jLabel1.setText("Callsign");

        jLabel11.setText("Callsign Ext.");

        callSignExt.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        callSignExt.setText("jTextField1");
        callSignExt.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                callSignExtActionPerformed(evt);
            }
        });
        callSignExt.addFocusListener(new java.awt.event.FocusAdapter()
        {
            public void focusLost(java.awt.event.FocusEvent evt)
            {
                callSignExtFocusLost(evt);
            }
        });

        javax.swing.GroupLayout callsignPanelLayout = new javax.swing.GroupLayout(callsignPanel);
        callsignPanel.setLayout(callsignPanelLayout);
        callsignPanelLayout.setHorizontalGroup(
            callsignPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(callsignPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(callsignPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel11))
                .addGap(18, 18, 18)
                .addGroup(callsignPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(callSign)
                    .addComponent(callSignExt))
                .addContainerGap(689, Short.MAX_VALUE))
        );
        callsignPanelLayout.setVerticalGroup(
            callsignPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(callsignPanelLayout.createSequentialGroup()
                .addGap(98, 98, 98)
                .addGroup(callsignPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(callSign, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(callsignPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(callSignExt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(126, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Callsign", callsignPanel);

        rptSettingsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Repeater"));

        rptSpinner.setModel(new javax.swing.SpinnerNumberModel(1, 1, 5, 1));
        rptSpinner.addChangeListener(new javax.swing.event.ChangeListener()
        {
            public void stateChanged(javax.swing.event.ChangeEvent evt)
            {
                rptSpinnerStateChanged(evt);
            }
        });

        jLabel9.setText("Use this repeater setting:");

        rptTable.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        rptTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {
                { new Integer(1), "DB0DF  B", "DB0DF  G"},
                { new Integer(2), "DB0DOS B", "DB0DOS G"},
                { new Integer(3), null, null},
                { new Integer(4), null, null},
                { new Integer(5), null, null}
            },
            new String []
            {
                "#", "RPT1", "RPT2"
            }
        )
        {
            Class[] types = new Class []
            {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean []
            {
                false, true, true
            };

            public Class getColumnClass(int columnIndex)
            {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex)
            {
                return canEdit [columnIndex];
            }
        });
        rptTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(rptTable);
        if (rptTable.getColumnModel().getColumnCount() > 0)
        {
            rptTable.getColumnModel().getColumn(0).setResizable(false);
            rptTable.getColumnModel().getColumn(0).setPreferredWidth(8);
        }

        rptDirectCheckbox.setText("direct QSO (don't use repeater)");
        rptDirectCheckbox.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                rptDirectCheckboxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout rptSettingsPanelLayout = new javax.swing.GroupLayout(rptSettingsPanel);
        rptSettingsPanel.setLayout(rptSettingsPanelLayout);
        rptSettingsPanelLayout.setHorizontalGroup(
            rptSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rptSettingsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(rptSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(rptSettingsPanelLayout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(rptSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(rptDirectCheckbox))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        rptSettingsPanelLayout.setVerticalGroup(
            rptSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rptSettingsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rptDirectCheckbox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addGroup(rptSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(rptSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        yourCallSettingsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("YOUR Call"));

        yourCallSpinner.setModel(new javax.swing.SpinnerNumberModel(1, 1, 10, 1));
        yourCallSpinner.addChangeListener(new javax.swing.event.ChangeListener()
        {
            public void stateChanged(javax.swing.event.ChangeEvent evt)
            {
                yourCallSpinnerStateChanged(evt);
            }
        });

        jLabel12.setText("Use this YOUR Call setting:");

        yourCallTable.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        yourCallTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {
                { new Integer(1), "CQCQCQ"},
                { new Integer(2), ""},
                { new Integer(3), null},
                { new Integer(4), null},
                { new Integer(5), null},
                { new Integer(6), ""},
                { new Integer(7), null},
                { new Integer(8), null},
                { new Integer(9), null},
                {null, null}
            },
            new String []
            {
                "#", "YOUR Call"
            }
        )
        {
            Class[] types = new Class []
            {
                java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean []
            {
                false, true
            };

            public Class getColumnClass(int columnIndex)
            {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex)
            {
                return canEdit [columnIndex];
            }
        });
        yourCallTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(yourCallTable);
        if (yourCallTable.getColumnModel().getColumnCount() > 0)
        {
            yourCallTable.getColumnModel().getColumn(0).setResizable(false);
            yourCallTable.getColumnModel().getColumn(0).setPreferredWidth(8);
        }

        javax.swing.GroupLayout yourCallSettingsPanelLayout = new javax.swing.GroupLayout(yourCallSettingsPanel);
        yourCallSettingsPanel.setLayout(yourCallSettingsPanelLayout);
        yourCallSettingsPanelLayout.setHorizontalGroup(
            yourCallSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(yourCallSettingsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(yourCallSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(yourCallSettingsPanelLayout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(18, 18, 18)
                        .addComponent(yourCallSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        yourCallSettingsPanelLayout.setVerticalGroup(
            yourCallSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, yourCallSettingsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(yourCallSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(yourCallSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        txmsgTextField.setText("jTextField1");
        txmsgTextField.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                txmsgTextFieldActionPerformed(evt);
            }
        });
        txmsgTextField.addFocusListener(new java.awt.event.FocusAdapter()
        {
            public void focusLost(java.awt.event.FocusEvent evt)
            {
                txmsgTextFieldFocusLost(evt);
            }
        });

        jLabel18.setText("TX Message (max. 20 chars)");

        javax.swing.GroupLayout dvPanelLayout = new javax.swing.GroupLayout(dvPanel);
        dvPanel.setLayout(dvPanelLayout);
        dvPanelLayout.setHorizontalGroup(
            dvPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dvPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dvPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dvPanelLayout.createSequentialGroup()
                        .addComponent(rptSettingsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(yourCallSettingsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(dvPanelLayout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txmsgTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(276, Short.MAX_VALUE))
        );
        dvPanelLayout.setVerticalGroup(
            dvPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dvPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dvPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(rptSettingsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(yourCallSettingsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(dvPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txmsgTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("DV", dvPanel);

        jLabel3.setText("TxDelay");

        phyTxDelay.setText("jTextField1");
        phyTxDelay.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                phyTxDelayActionPerformed(evt);
            }
        });
        phyTxDelay.addFocusListener(new java.awt.event.FocusAdapter()
        {
            public void focusLost(java.awt.event.FocusEvent evt)
            {
                phyTxDelayFocusLost(evt);
            }
        });

        jLabel4.setText("TxGain");

        phyTxGain.setText("jTextField1");
        phyTxGain.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                phyTxGainActionPerformed(evt);
            }
        });
        phyTxGain.addFocusListener(new java.awt.event.FocusAdapter()
        {
            public void focusLost(java.awt.event.FocusEvent evt)
            {
                phyTxGainFocusLost(evt);
            }
        });

        jLabel5.setText("TxDcShift");

        phyRxInv.setText("RxInv");
        phyRxInv.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                phyRxInvActionPerformed(evt);
            }
        });

        phyTxDcShift.setText("jTextField1");
        phyTxDcShift.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                phyTxDcShiftActionPerformed(evt);
            }
        });
        phyTxDcShift.addFocusListener(new java.awt.event.FocusAdapter()
        {
            public void focusLost(java.awt.event.FocusEvent evt)
            {
                phyTxDcShiftFocusLost(evt);
            }
        });

        phySoftwareVersion.setText("jLabel6");

        jLabel10.setText("RxDeviation");

        phyRxDevFactor.setText("jTextField1");
        phyRxDevFactor.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                phyRxDevFactorActionPerformed(evt);
            }
        });
        phyRxDevFactor.addFocusListener(new java.awt.event.FocusAdapter()
        {
            public void focusLost(java.awt.event.FocusEvent evt)
            {
                phyRxDevFactorFocusLost(evt);
            }
        });

        phySerialNumTextField.setEditable(false);
        phySerialNumTextField.setText("jTextField1");

        jLabel21.setText("S/N:");

        javax.swing.GroupLayout phyPanelLayout = new javax.swing.GroupLayout(phyPanel);
        phyPanel.setLayout(phyPanelLayout);
        phyPanelLayout.setHorizontalGroup(
            phyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(phyPanelLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(phyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(phyPanelLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18)
                        .addComponent(phyRxDevFactor, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(phyRxInv)
                    .addGroup(phyPanelLayout.createSequentialGroup()
                        .addGroup(phyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(30, 30, 30)
                        .addGroup(phyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(phyTxDcShift, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(phyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(phyPanelLayout.createSequentialGroup()
                                    .addComponent(phyTxGain, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel21)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(phySerialNumTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 443, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(phyPanelLayout.createSequentialGroup()
                                    .addComponent(phyTxDelay, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(70, 70, 70)
                                    .addComponent(phySoftwareVersion, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(115, Short.MAX_VALUE))
        );
        phyPanelLayout.setVerticalGroup(
            phyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(phyPanelLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(phyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(phyTxDelay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(phySoftwareVersion))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(phyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(phyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(phySerialNumTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel21))
                    .addGroup(phyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(phyTxGain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(phyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(phyTxDcShift, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(phyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(phyRxDevFactor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addComponent(phyRxInv)
                .addContainerGap(99, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("PHY", phyPanel);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("PTT Beep"));

        jLabel6.setText("Frequency");

        pttBeepFrequency.setText("jTextField1");
        pttBeepFrequency.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                pttBeepFrequencyActionPerformed(evt);
            }
        });
        pttBeepFrequency.addFocusListener(new java.awt.event.FocusAdapter()
        {
            public void focusLost(java.awt.event.FocusEvent evt)
            {
                pttBeepFrequencyFocusLost(evt);
            }
        });

        jLabel7.setText("Duration");

        pttBeepDuration.setText("jTextField1");
        pttBeepDuration.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                pttBeepDurationActionPerformed(evt);
            }
        });
        pttBeepDuration.addFocusListener(new java.awt.event.FocusAdapter()
        {
            public void focusLost(java.awt.event.FocusEvent evt)
            {
                pttBeepDurationFocusLost(evt);
            }
        });

        jLabel8.setText("Volume");

        pttBeepVolume.setText("jTextField2");
        pttBeepVolume.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                pttBeepVolumeActionPerformed(evt);
            }
        });
        pttBeepVolume.addFocusListener(new java.awt.event.FocusAdapter()
        {
            public void focusLost(java.awt.event.FocusEvent evt)
            {
                pttBeepVolumeFocusLost(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(pttBeepDuration, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
                    .addComponent(pttBeepFrequency, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pttBeepVolume))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pttBeepFrequency, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pttBeepDuration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(pttBeepVolume, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Standby Beep"));

        jLabel15.setText("Frequency");

        standbyBeepFrequency.setText("jTextField1");
        standbyBeepFrequency.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                standbyBeepFrequencyActionPerformed(evt);
            }
        });
        standbyBeepFrequency.addFocusListener(new java.awt.event.FocusAdapter()
        {
            public void focusLost(java.awt.event.FocusEvent evt)
            {
                standbyBeepFrequencyFocusLost(evt);
            }
        });

        jLabel16.setText("Duration");

        standbyBeepDuration.setText("jTextField1");
        standbyBeepDuration.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                standbyBeepDurationActionPerformed(evt);
            }
        });
        standbyBeepDuration.addFocusListener(new java.awt.event.FocusAdapter()
        {
            public void focusLost(java.awt.event.FocusEvent evt)
            {
                standbyBeepDurationFocusLost(evt);
            }
        });

        jLabel17.setText("Volume");

        standbyBeepVolume.setText("jTextField2");
        standbyBeepVolume.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                standbyBeepVolumeActionPerformed(evt);
            }
        });
        standbyBeepVolume.addFocusListener(new java.awt.event.FocusAdapter()
        {
            public void focusLost(java.awt.event.FocusEvent evt)
            {
                standbyBeepVolumeFocusLost(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(standbyBeepDuration, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
                    .addComponent(standbyBeepFrequency, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(standbyBeepVolume))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(standbyBeepFrequency, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(standbyBeepDuration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(standbyBeepVolume, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout audioPanelLayout = new javax.swing.GroupLayout(audioPanel);
        audioPanel.setLayout(audioPanelLayout);
        audioPanelLayout.setHorizontalGroup(
            audioPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(audioPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(475, Short.MAX_VALUE))
        );
        audioPanelLayout.setVerticalGroup(
            audioPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(audioPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(audioPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(158, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Audio", audioPanel);

        dprsEnableCheckBox.setText("enable outgoing D-PRS Messages");
        dprsEnableCheckBox.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                dprsEnableCheckBoxActionPerformed(evt);
            }
        });

        dprsSymbolComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Jogger", "Car", "House", "Boat", "Bicycle", "Van" }));
        dprsSymbolComboBox.addItemListener(new java.awt.event.ItemListener()
        {
            public void itemStateChanged(java.awt.event.ItemEvent evt)
            {
                dprsSymbolComboBoxItemStateChanged(evt);
            }
        });

        jLabel13.setText("D-PRS symbol");

        dprsTextField.setText("jTextField1");
        dprsTextField.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                dprsTextFieldActionPerformed(evt);
            }
        });
        dprsTextField.addFocusListener(new java.awt.event.FocusAdapter()
        {
            public void focusLost(java.awt.event.FocusEvent evt)
            {
                dprsTextFieldFocusLost(evt);
            }
        });

        jLabel14.setText("D-PRS Text (max 13 chars)");

        javax.swing.GroupLayout dprsPanelLayout = new javax.swing.GroupLayout(dprsPanel);
        dprsPanel.setLayout(dprsPanelLayout);
        dprsPanelLayout.setHorizontalGroup(
            dprsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dprsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dprsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dprsEnableCheckBox)
                    .addGroup(dprsPanelLayout.createSequentialGroup()
                        .addGroup(dprsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(jLabel14))
                        .addGap(30, 30, 30)
                        .addGroup(dprsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dprsSymbolComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dprsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(566, Short.MAX_VALUE))
        );
        dprsPanelLayout.setVerticalGroup(
            dprsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dprsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(dprsEnableCheckBox)
                .addGap(18, 18, 18)
                .addGroup(dprsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dprsSymbolComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addGap(33, 33, 33)
                .addGroup(dprsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dprsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addContainerGap(154, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("D-PRS", dprsPanel);

        backlightSlider.addChangeListener(new javax.swing.event.ChangeListener()
        {
            public void stateChanged(javax.swing.event.ChangeEvent evt)
            {
                backlightSliderStateChanged(evt);
            }
        });

        contrastSlider.addChangeListener(new javax.swing.event.ChangeListener()
        {
            public void stateChanged(javax.swing.event.ChangeEvent evt)
            {
                contrastSliderStateChanged(evt);
            }
        });

        jLabel19.setText("Backlight Intensity");

        jLabel20.setText("Contrast");

        remoteDisplayLabel.setToolTipText("");

        jButton1.setText("key1");
        jButton1.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("key2");
        jButton2.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("key3");
        jButton3.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("key4");
        jButton4.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("key5");
        jButton5.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("key6");
        jButton6.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton6ActionPerformed(evt);
            }
        });

        remoteScreenRefreshRateSlider.setMinimum(1);
        remoteScreenRefreshRateSlider.addChangeListener(new javax.swing.event.ChangeListener()
        {
            public void stateChanged(javax.swing.event.ChangeEvent evt)
            {
                remoteScreenRefreshRateSliderStateChanged(evt);
            }
        });

        remoteScreenComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Main", "GPS", "Reflector", "Debug", "Audio", "DVSet", "RMUSet" }));
        remoteScreenComboBox.addItemListener(new java.awt.event.ItemListener()
        {
            public void itemStateChanged(java.awt.event.ItemEvent evt)
            {
                remoteScreenComboBoxItemStateChanged(evt);
            }
        });

        jLabel23.setText("Refresh Rate");

        jLabel24.setText("Screen");

        javax.swing.GroupLayout displayPanelLayout = new javax.swing.GroupLayout(displayPanel);
        displayPanel.setLayout(displayPanelLayout);
        displayPanelLayout.setHorizontalGroup(
            displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(displayPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19)
                    .addComponent(jLabel20)
                    .addComponent(jLabel23)
                    .addComponent(jLabel24))
                .addGap(18, 18, 18)
                .addGroup(displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(contrastSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(backlightSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(remoteScreenRefreshRateSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(remoteScreenComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(displayPanelLayout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(57, 57, 57)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3)
                        .addGap(57, 57, 57)
                        .addComponent(jButton4))
                    .addComponent(remoteDisplayLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton5)
                    .addComponent(jButton6))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        displayPanelLayout.setVerticalGroup(
            displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(displayPanelLayout.createSequentialGroup()
                .addGroup(displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(displayPanelLayout.createSequentialGroup()
                        .addGroup(displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(displayPanelLayout.createSequentialGroup()
                                .addGap(48, 48, 48)
                                .addGroup(displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(backlightSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel19))
                                .addGap(18, 18, 18)
                                .addGroup(displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(contrastSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel20))
                                .addGap(31, 31, 31))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, displayPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jButton6)
                                .addGap(57, 57, 57)))
                        .addGroup(displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton5)
                            .addGroup(displayPanelLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(remoteScreenComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel24)))))
                    .addGroup(displayPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(remoteDisplayLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(displayPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1)
                            .addComponent(jButton3)
                            .addComponent(jButton2)
                            .addComponent(jButton4)))
                    .addGroup(displayPanelLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel23)
                            .addComponent(remoteScreenRefreshRateSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Display", displayPanel);

        jLabel2.setText("Input voltage");

        inputVoltage.setText("voltage");

        jLabel22.setText("SNMP Community String");

        snmpCmnty.setEditable(false);
        snmpCmnty.setText("jTextField1");

        javax.swing.GroupLayout debugPanelLayout = new javax.swing.GroupLayout(debugPanel);
        debugPanel.setLayout(debugPanelLayout);
        debugPanelLayout.setHorizontalGroup(
            debugPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(debugPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(debugPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel22)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(debugPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(inputVoltage)
                    .addComponent(snmpCmnty, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(336, Short.MAX_VALUE))
        );
        debugPanelLayout.setVerticalGroup(
            debugPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(debugPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(debugPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(inputVoltage))
                .addGap(38, 38, 38)
                .addGroup(debugPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(snmpCmnty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(192, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Debug", debugPanel);

        myIP.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                myIPActionPerformed(evt);
            }
        });
        myIP.addFocusListener(new java.awt.event.FocusAdapter()
        {
            public void focusLost(java.awt.event.FocusEvent evt)
            {
                myIPFocusLost(evt);
            }
        });

        netmaskIP.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                netmaskIPActionPerformed(evt);
            }
        });
        netmaskIP.addFocusListener(new java.awt.event.FocusAdapter()
        {
            public void focusLost(java.awt.event.FocusEvent evt)
            {
                netmaskIPFocusLost(evt);
            }
        });

        gwIP.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                gwIPActionPerformed(evt);
            }
        });
        gwIP.addFocusListener(new java.awt.event.FocusAdapter()
        {
            public void focusLost(java.awt.event.FocusEvent evt)
            {
                gwIPFocusLost(evt);
            }
        });

        DNS_IP1.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                DNS_IP1ActionPerformed(evt);
            }
        });
        DNS_IP1.addFocusListener(new java.awt.event.FocusAdapter()
        {
            public void focusLost(java.awt.event.FocusEvent evt)
            {
                DNS_IP1FocusLost(evt);
            }
        });

        DNS_IP2.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                DNS_IP2ActionPerformed(evt);
            }
        });
        DNS_IP2.addFocusListener(new java.awt.event.FocusAdapter()
        {
            public void focusLost(java.awt.event.FocusEvent evt)
            {
                DNS_IP2FocusLost(evt);
            }
        });

        NTP_IP.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                NTP_IPActionPerformed(evt);
            }
        });
        NTP_IP.addFocusListener(new java.awt.event.FocusAdapter()
        {
            public void focusLost(java.awt.event.FocusEvent evt)
            {
                NTP_IPFocusLost(evt);
            }
        });

        jLabel25.setText("IP address");

        jLabel26.setText("netmask");

        jLabel27.setText("gateway");

        jLabel28.setText("DNS server 1");

        jLabel29.setText("DNS server 2");

        jLabel30.setText("NTP server");

        useAltDNS.setText("use alternative DNS domain for DCS");
        useAltDNS.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                useAltDNSActionPerformed(evt);
            }
        });

        enableNTP.setText("enable NTP");
        enableNTP.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                enableNTPActionPerformed(evt);
            }
        });

        useOnlyTenMBit.setText("use only 10 MBit/s");
        useOnlyTenMBit.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                useOnlyTenMBitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout networkPanelLayout = new javax.swing.GroupLayout(networkPanel);
        networkPanel.setLayout(networkPanelLayout);
        networkPanelLayout.setHorizontalGroup(
            networkPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(networkPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(networkPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel25)
                    .addComponent(jLabel26)
                    .addComponent(jLabel27)
                    .addComponent(jLabel28)
                    .addComponent(jLabel29)
                    .addComponent(jLabel30))
                .addGap(18, 18, 18)
                .addGroup(networkPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(gwIP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DNS_IP1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DNS_IP2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(networkPanelLayout.createSequentialGroup()
                        .addComponent(NTP_IP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(enableNTP))
                    .addGroup(networkPanelLayout.createSequentialGroup()
                        .addGroup(networkPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(myIP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(netmaskIP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(129, 129, 129)
                        .addGroup(networkPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(useAltDNS)
                            .addComponent(useOnlyTenMBit))))
                .addContainerGap(292, Short.MAX_VALUE))
        );
        networkPanelLayout.setVerticalGroup(
            networkPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(networkPanelLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(networkPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(myIP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(useAltDNS))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(networkPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(netmaskIP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26)
                    .addComponent(useOnlyTenMBit))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(networkPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(gwIP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(networkPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(DNS_IP1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(networkPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(DNS_IP2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(networkPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(NTP_IP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(enableNTP))
                .addContainerGap(86, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Network", networkPanel);

        ExitButton.setText("Exit");
        ExitButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                ExitButtonActionPerformed(evt);
            }
        });

        saveToFlash.setText("Save to Flash Memory");
        saveToFlash.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                saveToFlashActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout configFrameLayout = new javax.swing.GroupLayout(configFrame.getContentPane());
        configFrame.getContentPane().setLayout(configFrameLayout);
        configFrameLayout.setHorizontalGroup(
            configFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, configFrameLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(saveToFlash)
                .addGap(18, 18, 18)
                .addComponent(ExitButton)
                .addGap(14, 14, 14))
        );
        configFrameLayout.setVerticalGroup(
            configFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(configFrameLayout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(configFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saveToFlash)
                    .addComponent(ExitButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        desktopPane.add(configFrame);
        configFrame.setBounds(10, 15, 880, 372);

        loadingFrame.setVisible(false);

        loadProgressLabel.setText("jLabel2");

        loadCancelButton.setText("Cancel");

        javax.swing.GroupLayout loadingFrameLayout = new javax.swing.GroupLayout(loadingFrame.getContentPane());
        loadingFrame.getContentPane().setLayout(loadingFrameLayout);
        loadingFrameLayout.setHorizontalGroup(
            loadingFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loadingFrameLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(loadingFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(loadingFrameLayout.createSequentialGroup()
                        .addComponent(loadProgressLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 110, Short.MAX_VALUE))
                    .addComponent(loadProgressBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loadingFrameLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(loadCancelButton)))
                .addContainerGap())
        );
        loadingFrameLayout.setVerticalGroup(
            loadingFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loadingFrameLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(loadProgressLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(loadProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(loadCancelButton)
                .addContainerGap(105, Short.MAX_VALUE))
        );

        desktopPane.add(loadingFrame);
        loadingFrame.setBounds(200, 200, 340, 212);

        updateFrame.setTitle("Firmware Update");
        updateFrame.setVisible(false);

        updateLabel1.setText("Transferring file to UP4DAR board...");

        updateOKButton.setText("OK");
        updateOKButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                updateOKButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout updateFrameLayout = new javax.swing.GroupLayout(updateFrame.getContentPane());
        updateFrame.getContentPane().setLayout(updateFrameLayout);
        updateFrameLayout.setHorizontalGroup(
            updateFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(updateFrameLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(updateFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(updateLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                    .addComponent(updateProgressBar, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                    .addComponent(updateLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(updateOKButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        updateFrameLayout.setVerticalGroup(
            updateFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(updateFrameLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(updateFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(updateOKButton)
                    .addGroup(updateFrameLayout.createSequentialGroup()
                        .addComponent(updateLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(updateProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(updateLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        desktopPane.add(updateFrame);
        updateFrame.setBounds(90, 300, 335, 235);

        fileMenu.setMnemonic('f');
        fileMenu.setText("File");

        openMenuItem.setMnemonic('o');
        openMenuItem.setText("Open");
        openMenuItem.setEnabled(false);
        fileMenu.add(openMenuItem);

        saveMenuItem.setMnemonic('s');
        saveMenuItem.setText("Save");
        saveMenuItem.setEnabled(false);
        fileMenu.add(saveMenuItem);

        saveAsMenuItem.setMnemonic('a');
        saveAsMenuItem.setText("Save As ...");
        saveAsMenuItem.setDisplayedMnemonicIndex(5);
        saveAsMenuItem.setEnabled(false);
        fileMenu.add(saveAsMenuItem);

        updateMenuItem.setText("Firmware Update");
        updateMenuItem.setEnabled(false);
        updateMenuItem.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                updateMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(updateMenuItem);

        exitMenuItem.setMnemonic('x');
        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                exitMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        editMenu.setMnemonic('e');
        editMenu.setText("Edit");

        cutMenuItem.setMnemonic('t');
        cutMenuItem.setText("Cut");
        cutMenuItem.setEnabled(false);
        editMenu.add(cutMenuItem);

        copyMenuItem.setMnemonic('y');
        copyMenuItem.setText("Copy");
        copyMenuItem.setEnabled(false);
        editMenu.add(copyMenuItem);

        pasteMenuItem.setMnemonic('p');
        pasteMenuItem.setText("Paste");
        pasteMenuItem.setEnabled(false);
        editMenu.add(pasteMenuItem);

        deleteMenuItem.setMnemonic('d');
        deleteMenuItem.setText("Delete");
        deleteMenuItem.setEnabled(false);
        editMenu.add(deleteMenuItem);

        menuBar.add(editMenu);

        helpMenu.setMnemonic('h');
        helpMenu.setText("Help");

        contentMenuItem.setMnemonic('c');
        contentMenuItem.setText("Contents");
        contentMenuItem.setEnabled(false);
        contentMenuItem.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                contentMenuItemActionPerformed(evt);
            }
        });
        helpMenu.add(contentMenuItem);

        aboutMenuItem.setMnemonic('a');
        aboutMenuItem.setText("About");
        aboutMenuItem.setEnabled(false);
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.PREFERRED_SIZE, 900, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitMenuItemActionPerformed

    private void contentMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contentMenuItemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_contentMenuItemActionPerformed

    private void boardListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_boardListValueChanged
        
        if ((snmp == null) && (boardList.getSelectedIndex() >= 0))
        {
            connectButton.setEnabled( true );
        }
    }//GEN-LAST:event_boardListValueChanged

    private void callSignActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_callSignActionPerformed
    {//GEN-HEADEREND:event_callSignActionPerformed
        try
        {
            snmp.snmpSetString("30", callSign.getText());
     
        } catch (Exception ex)
        {
            // perhaps format was wrong
        }
        
        try
        {
            callSign.setText(snmp.snmpGetString("30"));
            
        } catch (Exception ex)
        {
            Logger.getLogger(UP4DAR_Configurator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_callSignActionPerformed

    private void connectButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_connectButtonActionPerformed
    {//GEN-HEADEREND:event_connectButtonActionPerformed
        connectButton.setEnabled( false );
        
        if (boardList.getSelectedIndex() >= bcRX.getSize() )
        {
            
            boardList.clearSelection();
        }
        else
        {
            try
            {
                doConnect( bcRX.getIP(boardList.getSelectedIndex()),
                       bcRX.getCmnty(boardList.getSelectedIndex() ));
                
            } catch (Exception ex)
            {
                Logger.getLogger(UP4DAR_Configurator.class.getName()).log(Level.SEVERE, null, ex);
                connectButton.setEnabled( true );
            }
        }
        
    }//GEN-LAST:event_connectButtonActionPerformed

    private void ExitButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_ExitButtonActionPerformed
    {//GEN-HEADEREND:event_ExitButtonActionPerformed
        configFrame.setVisible(false);
        connectButton.setEnabled( false );
        boardList.clearSelection();
        System.exit(0);
    }//GEN-LAST:event_ExitButtonActionPerformed

    private void phyTxDelayActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_phyTxDelayActionPerformed
    {//GEN-HEADEREND:event_phyTxDelayActionPerformed
        try
        {          
            snmp.snmpSetInteger("230", Integer.parseInt( phyTxDelay.getText()));
     
        } catch (Exception ex)
        {
            // perhaps format was wrong
        }
        
        try
        {
            phyTxDelay.setText( Integer.toString( snmp.snmpGetInteger("230") ));
                    
        } catch (Exception ex)
        {
            Logger.getLogger(UP4DAR_Configurator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_phyTxDelayActionPerformed

    private void phyRxInvActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_phyRxInvActionPerformed
    {//GEN-HEADEREND:event_phyRxInvActionPerformed
        try
        {
            int value;
            
            if (phyRxInv.isSelected())
            {
                value = 1;
            }
            else
            {
                value = 0;
            }
            
            snmp.snmpSetInteger("250", value);
     
        } catch (Exception ex)
        {
            // perhaps format was wrong
        }
        
        try
        {
            phyRxInv.setSelected(snmp.snmpGetInteger("250") == 1);
                    
        } catch (Exception ex)
        {
            Logger.getLogger(UP4DAR_Configurator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_phyRxInvActionPerformed

    private void phyTxGainActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_phyTxGainActionPerformed
    {//GEN-HEADEREND:event_phyTxGainActionPerformed
         try
        {          
            snmp.snmpSetInteger("240", Integer.parseInt( phyTxGain.getText()));
     
        } catch (Exception ex)
        {
            // perhaps format was wrong
        }
        
        try
        {
            phyTxGain.setText( Integer.toString( snmp.snmpGetInteger("240") ));
                    
        } catch (Exception ex)
        {
            Logger.getLogger(UP4DAR_Configurator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_phyTxGainActionPerformed

    private void phyTxDcShiftActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_phyTxDcShiftActionPerformed
    {//GEN-HEADEREND:event_phyTxDcShiftActionPerformed
         try
        {          
            snmp.snmpSetInteger("260", Integer.parseInt( phyTxDcShift.getText()));
     
        } catch (Exception ex)
        {
            // perhaps format was wrong
        }
        
        try
        {
            phyTxDcShift.setText( Integer.toString( snmp.snmpGetInteger("260") ));
                    
        } catch (Exception ex)
        {
            Logger.getLogger(UP4DAR_Configurator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_phyTxDcShiftActionPerformed

    private void phyTxDcShiftFocusLost(java.awt.event.FocusEvent evt)//GEN-FIRST:event_phyTxDcShiftFocusLost
    {//GEN-HEADEREND:event_phyTxDcShiftFocusLost
        phyTxDcShiftActionPerformed(null);
    }//GEN-LAST:event_phyTxDcShiftFocusLost

    private void phyTxGainFocusLost(java.awt.event.FocusEvent evt)//GEN-FIRST:event_phyTxGainFocusLost
    {//GEN-HEADEREND:event_phyTxGainFocusLost
        phyTxGainActionPerformed(null);
    }//GEN-LAST:event_phyTxGainFocusLost

    private void phyTxDelayFocusLost(java.awt.event.FocusEvent evt)//GEN-FIRST:event_phyTxDelayFocusLost
    {//GEN-HEADEREND:event_phyTxDelayFocusLost
        phyTxDelayActionPerformed(null);
    }//GEN-LAST:event_phyTxDelayFocusLost

    private void callSignFocusLost(java.awt.event.FocusEvent evt)//GEN-FIRST:event_callSignFocusLost
    {//GEN-HEADEREND:event_callSignFocusLost
        callSignActionPerformed(null);
    }//GEN-LAST:event_callSignFocusLost

    private void saveToFlashActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_saveToFlashActionPerformed
    {//GEN-HEADEREND:event_saveToFlashActionPerformed
        try
        {          
            snmp.snmpSetInteger("50", 2);
     
        } catch (Exception ex)
        {
            Logger.getLogger(UP4DAR_Configurator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_saveToFlashActionPerformed

    private void pttBeepFrequencyActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_pttBeepFrequencyActionPerformed
    {//GEN-HEADEREND:event_pttBeepFrequencyActionPerformed
        try
        {          
            snmp.snmpSetInteger("650", Integer.parseInt( pttBeepFrequency.getText()));
     
        } catch (Exception ex)
        {
            // perhaps format was wrong
        }
        
        try
        {
            pttBeepFrequency.setText( Integer.toString( snmp.snmpGetInteger("650") ));
                    
        } catch (Exception ex)
        {
            Logger.getLogger(UP4DAR_Configurator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_pttBeepFrequencyActionPerformed

    private void pttBeepDurationActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_pttBeepDurationActionPerformed
    {//GEN-HEADEREND:event_pttBeepDurationActionPerformed
        try
        {          
            snmp.snmpSetInteger("640", Integer.parseInt( pttBeepDuration.getText()));
     
        } catch (Exception ex)
        {
            // perhaps format was wrong
        }
        
        try
        {
            pttBeepDuration.setText( Integer.toString( snmp.snmpGetInteger("640") ));
                    
        } catch (Exception ex)
        {
            Logger.getLogger(UP4DAR_Configurator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_pttBeepDurationActionPerformed

    private void pttBeepVolumeActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_pttBeepVolumeActionPerformed
    {//GEN-HEADEREND:event_pttBeepVolumeActionPerformed
        try
        {          
            snmp.snmpSetInteger("660", Integer.parseInt( pttBeepVolume.getText()));
     
        } catch (Exception ex)
        {
            // perhaps format was wrong
        }
        
        try
        {
            pttBeepVolume.setText( Integer.toString( snmp.snmpGetInteger("660") ));
                    
        } catch (Exception ex)
        {
            Logger.getLogger(UP4DAR_Configurator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_pttBeepVolumeActionPerformed

    private void pttBeepFrequencyFocusLost(java.awt.event.FocusEvent evt)//GEN-FIRST:event_pttBeepFrequencyFocusLost
    {//GEN-HEADEREND:event_pttBeepFrequencyFocusLost
        pttBeepFrequencyActionPerformed(null);
    }//GEN-LAST:event_pttBeepFrequencyFocusLost

    private void pttBeepDurationFocusLost(java.awt.event.FocusEvent evt)//GEN-FIRST:event_pttBeepDurationFocusLost
    {//GEN-HEADEREND:event_pttBeepDurationFocusLost
        pttBeepDurationActionPerformed(null);
    }//GEN-LAST:event_pttBeepDurationFocusLost

    private void pttBeepVolumeFocusLost(java.awt.event.FocusEvent evt)//GEN-FIRST:event_pttBeepVolumeFocusLost
    {//GEN-HEADEREND:event_pttBeepVolumeFocusLost
        pttBeepVolumeActionPerformed(null);
    }//GEN-LAST:event_pttBeepVolumeFocusLost

    private void rptSpinnerStateChanged(javax.swing.event.ChangeEvent evt)//GEN-FIRST:event_rptSpinnerStateChanged
    {//GEN-HEADEREND:event_rptSpinnerStateChanged
        try
        {          
            snmp.snmpSetInteger("710", (Integer) rptSpinner.getValue());
     
        } catch (Exception ex)
        {
            // perhaps format was wrong
        }
        
        try
        {
            rptSpinner.setValue(snmp.snmpGetInteger("710"));
                    
        } catch (Exception ex)
        {
            Logger.getLogger(UP4DAR_Configurator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_rptSpinnerStateChanged

    private void phyRxDevFactorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_phyRxDevFactorActionPerformed
        try
        {          
            snmp.snmpSetInteger("290", Integer.parseInt( phyRxDevFactor.getText()));
     
        } catch (Exception ex)
        {
            // perhaps format was wrong
        }
        
        try
        {
            phyRxDevFactor.setText( Integer.toString( snmp.snmpGetInteger("290") ));
                    
        } catch (Exception ex)
        {
            Logger.getLogger(UP4DAR_Configurator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_phyRxDevFactorActionPerformed

    private void phyRxDevFactorFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_phyRxDevFactorFocusLost
        phyRxDevFactorActionPerformed(null);
    }//GEN-LAST:event_phyRxDevFactorFocusLost

    private void callSignExtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_callSignExtActionPerformed
        try
        {
            snmp.snmpSetString("750", callSignExt.getText());
     
        } catch (Exception ex)
        {
            // perhaps format was wrong
        }
        
        try
        {
            callSignExt.setText(snmp.snmpGetString("750"));
            
        } catch (Exception ex)
        {
            Logger.getLogger(UP4DAR_Configurator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_callSignExtActionPerformed

    private void callSignExtFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_callSignExtFocusLost
        callSignExtActionPerformed(null);
    }//GEN-LAST:event_callSignExtFocusLost

    private void rptDirectCheckboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rptDirectCheckboxActionPerformed
        try
        {
            int value;
            
            if (rptDirectCheckbox.isSelected())
            {
                value = 1;
            }
            else
            {
                value = 0;
            }
            
            snmp.snmpSetInteger("760", value);
     
        } catch (Exception ex)
        {
            // perhaps format was wrong
        }
        
        try
        {
            rptDirectCheckbox.setSelected(snmp.snmpGetInteger("760") == 1);
            
            rptSpinner.setEnabled(! rptDirectCheckbox.isSelected());
            // rptTable.setEnabled(! rptDirectCheckbox.isSelected());
            
                    
        } catch (Exception ex)
        {
            Logger.getLogger(UP4DAR_Configurator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_rptDirectCheckboxActionPerformed

    private void yourCallSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_yourCallSpinnerStateChanged
        try
        {          
            snmp.snmpSetInteger("730", (Integer) yourCallSpinner.getValue());
     
        } catch (Exception ex)
        {
            // perhaps format was wrong
        }
        
        try
        {
            yourCallSpinner.setValue(snmp.snmpGetInteger("730"));
                    
        } catch (Exception ex)
        {
            Logger.getLogger(UP4DAR_Configurator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_yourCallSpinnerStateChanged

    private void standbyBeepFrequencyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_standbyBeepFrequencyActionPerformed
        try
        {          
            snmp.snmpSetInteger("620", Integer.parseInt( standbyBeepFrequency.getText()));
     
        } catch (Exception ex)
        {
            // perhaps format was wrong
        }
        
        try
        {
            standbyBeepFrequency.setText( Integer.toString( snmp.snmpGetInteger("620") ));
                    
        } catch (Exception ex)
        {
            Logger.getLogger(UP4DAR_Configurator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_standbyBeepFrequencyActionPerformed

    private void standbyBeepFrequencyFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_standbyBeepFrequencyFocusLost
        standbyBeepFrequencyActionPerformed(null);
    }//GEN-LAST:event_standbyBeepFrequencyFocusLost

    private void standbyBeepDurationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_standbyBeepDurationActionPerformed
        try
        {          
            snmp.snmpSetInteger("610", Integer.parseInt( standbyBeepDuration.getText()));
     
        } catch (Exception ex)
        {
            // perhaps format was wrong
        }
        
        try
        {
            standbyBeepDuration.setText( Integer.toString( snmp.snmpGetInteger("610") ));
                    
        } catch (Exception ex)
        {
            Logger.getLogger(UP4DAR_Configurator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_standbyBeepDurationActionPerformed

    private void standbyBeepDurationFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_standbyBeepDurationFocusLost
        standbyBeepDurationActionPerformed(null);
    }//GEN-LAST:event_standbyBeepDurationFocusLost

    private void standbyBeepVolumeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_standbyBeepVolumeActionPerformed
        try
        {          
            snmp.snmpSetInteger("630", Integer.parseInt( standbyBeepVolume.getText()));
     
        } catch (Exception ex)
        {
            // perhaps format was wrong
        }
        
        try
        {
            standbyBeepVolume.setText( Integer.toString( snmp.snmpGetInteger("630") ));
                    
        } catch (Exception ex)
        {
            Logger.getLogger(UP4DAR_Configurator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_standbyBeepVolumeActionPerformed

    private void standbyBeepVolumeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_standbyBeepVolumeFocusLost
        standbyBeepVolumeActionPerformed(null);
    }//GEN-LAST:event_standbyBeepVolumeFocusLost

    private void txmsgTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txmsgTextFieldActionPerformed
        try
        {
            snmp.snmpSetString("770", txmsgTextField.getText());
     
        } catch (Exception ex)
        {
            // perhaps format was wrong
        }
        
        try
        {
            txmsgTextField.setText(snmp.snmpGetString("770"));
            
        } catch (Exception ex)
        {
            Logger.getLogger(UP4DAR_Configurator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_txmsgTextFieldActionPerformed

    private void backlightSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_backlightSliderStateChanged
        try
        {          
            snmp.snmpSetInteger("920", (Integer) backlightSlider.getValue());
     
        } catch (Exception ex)
        {
            // perhaps format was wrong
        }
        
        try
        {
            backlightSlider.setValue(snmp.snmpGetInteger("920"));
                    
        } catch (Exception ex)
        {
            Logger.getLogger(UP4DAR_Configurator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_backlightSliderStateChanged

    private void contrastSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_contrastSliderStateChanged
        try
        {          
            snmp.snmpSetInteger("910", (Integer) contrastSlider.getValue());
     
        } catch (Exception ex)
        {
            // perhaps format was wrong
        }
        
        try
        {
            contrastSlider.setValue(snmp.snmpGetInteger("910"));
                    
        } catch (Exception ex)
        {
            Logger.getLogger(UP4DAR_Configurator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_contrastSliderStateChanged

    
    
    private void setCheckBox(JCheckBox cb, String snmpvar)
    {
        try
        {
            int value;
            
            if (cb.isSelected())
            {
                value = 1;
            }
            else
            {
                value = 0;
            }
            
            snmp.snmpSetInteger(snmpvar, value);
     
        } catch (Exception ex)
        {
            // perhaps format was wrong
        }
        
        try
        {
            cb.setSelected(snmp.snmpGetInteger(snmpvar) == 1);
                    
        } catch (Exception ex)
        {
            Logger.getLogger(UP4DAR_Configurator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    private void dprsEnableCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dprsEnableCheckBoxActionPerformed
       
        setCheckBox(dprsEnableCheckBox, "810");
        
    }//GEN-LAST:event_dprsEnableCheckBoxActionPerformed

    private void dprsSymbolComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_dprsSymbolComboBoxItemStateChanged
       
        try
        {          
            snmp.snmpSetInteger("820", dprsSymbolComboBox.getSelectedIndex());
     
        } catch (Exception ex)
        {
            // perhaps format was wrong
        }
        
        /*
        try
        {
            dprsSymbolComboBox.setSelectedIndex(snmp.snmpGetInteger("820"));
                    
        } catch (Exception ex)
        {
            Logger.getLogger(UP4DAR_Configurator.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        */
    }//GEN-LAST:event_dprsSymbolComboBoxItemStateChanged

    private void dprsTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dprsTextFieldActionPerformed
        try
        {
            snmp.snmpSetString("830", dprsTextField.getText());
     
        } catch (Exception ex)
        {
            // perhaps format was wrong
        }
        
        try
        {
            dprsTextField.setText(snmp.snmpGetString("830"));
            
        } catch (Exception ex)
        {
            Logger.getLogger(UP4DAR_Configurator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_dprsTextFieldActionPerformed

    private void dprsTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dprsTextFieldFocusLost
        dprsTextFieldActionPerformed(null);
    }//GEN-LAST:event_dprsTextFieldFocusLost

    private void txmsgTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txmsgTextFieldFocusLost
        txmsgTextFieldActionPerformed(null);
    }//GEN-LAST:event_txmsgTextFieldFocusLost

    
    
    
    
    
    class FirmwareUpdateTask extends SwingWorker<Void, Void> {
        
        
        boolean errorOccured = false;
        java.io.File firmwareFile;
        int num_blocks;
        
        public FirmwareUpdateTask( java.io.File f )
        {
            super();
            firmwareFile = f;
            
            long n = firmwareFile.length() / 512;
        
            if (n > 1000)
            {
                num_blocks = 1000;
                
              
            }
            else
            {
                num_blocks = (int) n;
            }
            
        }
        
        void progressUpdate(int block)
        {
            int percent = ((block + 1) * 100) / num_blocks;
            setProgress(percent);
        }
        /*
         * Main task. Executed in background thread.
         */
        @Override
        public Void doInBackground()
        {
            
            try
            {
                java.io.FileInputStream f = new java.io.FileInputStream(firmwareFile);
                    
                int i;
                
                byte[] buf = new byte[514];
                
                for (i=0; i < num_blocks; i++)
                {
                    if (f.read(buf, 2, 512) != 512)
                    {
                        errorOccured = true;
                        break;
                    }
                    
                    int last_block_bit = 0;
                    
                    if (i == (num_blocks-1))
                    {
                        last_block_bit = 0x80;
                    }
                
                    buf[0] = (byte) ((i >> 8) | last_block_bit);
                    buf[1] = (byte) (i & 0xFF);
                    
                    snmp.snmpSetBinaryString("150", buf );
                    progressUpdate(i);
                }
                
                byte[] result = snmp.snmpGetBinaryString("150");
                
                if (result.length != 26)
                {
                    errorOccured = true;
                }
                else
                {
                    int imageType = result[0] & 0x03;
                    
                    if (imageType < 1)
                    {
                        errorOccured = true;
                    }
                }
                
                f.close();
                
            } catch (Exception ex)
            {
                Logger.getLogger(UP4DAR_Configurator.class.getName()).log(Level.SEVERE, null, ex);
                errorOccured = true;
            }
           
            return null;
        }

        /*
         * Executed in event dispatching thread
         */
        @Override
        public void done()
        {
            // setCursor(null); //turn off the wait cursor
            updateOKButton.setEnabled(true);
            
            if (errorOccured)
            {
                updateLabel1.setText("ERROR: ");
            }
            else
            {
                updateLabel1.setText("Transfer completed.");
                updateLabel2.setText("Please REBOOT the board.");
            }
           
        }
    }
    
    
    
    
    FirmwareUpdateTask firmwareUpdateTask;
    
    private void updateMenuItemActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_updateMenuItemActionPerformed
    {//GEN-HEADEREND:event_updateMenuItemActionPerformed
       
        updateFileChooser.setFileFilter(new  javax.swing.filechooser.FileFilter() {
            @Override
                public boolean accept(java.io.File f) {
                    return f.getName().toLowerCase().endsWith(".bin") || f.isDirectory();
                }
            @Override
                public String getDescription() {
                    return "Firmware Files (*.bin)";
                }
            }); 
        
        int returnVal = updateFileChooser.showOpenDialog(this);
        if  (returnVal == javax.swing.JFileChooser.APPROVE_OPTION) {
        // System.out.println("You chose to open this file: " +
         //       updateFileChooser.getSelectedFile().length());
        
          updateLabel1.setText("Transferring file to UP4DAR board...");
          updateLabel2.setText("");  
          updateOKButton.setEnabled(false);
          updateFrame.setVisible(true);
          
          firmwareUpdateTask = new FirmwareUpdateTask(updateFileChooser.getSelectedFile());
          firmwareUpdateTask.addPropertyChangeListener( new PropertyChangeListener() {

            @Override
            public void propertyChange(PropertyChangeEvent pce)
            {
                if (pce.getPropertyName().equals("progress"))
                {
                    int progress = (Integer) pce.getNewValue();
                    updateProgressBar.setValue(progress);
                } 
            }
           });
           firmwareUpdateTask.execute();
         }
      
    }//GEN-LAST:event_updateMenuItemActionPerformed

    private void updateOKButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_updateOKButtonActionPerformed
    {//GEN-HEADEREND:event_updateOKButtonActionPerformed
        
        updateFrame.setVisible(false);
    }//GEN-LAST:event_updateOKButtonActionPerformed

    static final String remoteDisplayButtons[] = {
    
      "18210",  // Main
      "18220",  // GPS
      "18230",  // Reflector
      "18240",  // Debug
      "18260",  // Audio
      "18270",  // DVSet
      "18280"   // RMUSet
   };
    
    static final String remoteDisplayScreens[] = {
    
      "18110",  // Main
      "18120",  // GPS
      "18130",  // Reflector
      "18140",  // Debug
      "18160",  // Audio
      "18170",  // DVSet
      "18180"   // RMUSet
   };
    
    private void remoteScreenComboBoxItemStateChanged(java.awt.event.ItemEvent evt)//GEN-FIRST:event_remoteScreenComboBoxItemStateChanged
    {//GEN-HEADEREND:event_remoteScreenComboBoxItemStateChanged
       
        try
        {
            remoteDisplay.updatePixel(snmp.snmpGetBinaryString(
                    remoteDisplayScreens[remoteScreenComboBox.getSelectedIndex()]));
        }
        catch (Exception e)
        {
        }
    }//GEN-LAST:event_remoteScreenComboBoxItemStateChanged

    private void remoteScreenRefreshRateSliderStateChanged(javax.swing.event.ChangeEvent evt)//GEN-FIRST:event_remoteScreenRefreshRateSliderStateChanged
    {//GEN-HEADEREND:event_remoteScreenRefreshRateSliderStateChanged
        
        remoteDisplayRefreshTimer.setDelay(100 * remoteScreenRefreshRateSlider.getValue());
        remoteDisplayRefreshTimer.restart();
        
    }//GEN-LAST:event_remoteScreenRefreshRateSliderStateChanged

    private void remote_control_button_pressed(int num)
    {
        try
        {
            snmp.snmpSetInteger(
               remoteDisplayButtons[remoteScreenComboBox.getSelectedIndex()],
               num );
        }
        catch (Exception e)
        {
        }
    }
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton1ActionPerformed
    {//GEN-HEADEREND:event_jButton1ActionPerformed
        remote_control_button_pressed(1);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton2ActionPerformed
    {//GEN-HEADEREND:event_jButton2ActionPerformed
        remote_control_button_pressed(2);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton3ActionPerformed
    {//GEN-HEADEREND:event_jButton3ActionPerformed
        remote_control_button_pressed(3);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton4ActionPerformed
    {//GEN-HEADEREND:event_jButton4ActionPerformed
        remote_control_button_pressed(0);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton5ActionPerformed
    {//GEN-HEADEREND:event_jButton5ActionPerformed
        remote_control_button_pressed(5);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton6ActionPerformed
    {//GEN-HEADEREND:event_jButton6ActionPerformed
        remote_control_button_pressed(4);
    }//GEN-LAST:event_jButton6ActionPerformed

    
    private void setIpAddr (JIpTextField tf, String snmpVar)
    {
 
        try
        {
            byte[] dataBytes = tf.getIpAddress().getAddress();
            
            snmp.snmpSetBinaryString(snmpVar, dataBytes);
        }
        catch (Exception e)
        {
        }
    }
    
    private void myIPActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_myIPActionPerformed
    {//GEN-HEADEREND:event_myIPActionPerformed
        setIpAddr(myIP, "1710");
    }//GEN-LAST:event_myIPActionPerformed

    private void myIPFocusLost(java.awt.event.FocusEvent evt)//GEN-FIRST:event_myIPFocusLost
    {//GEN-HEADEREND:event_myIPFocusLost
        myIPActionPerformed(null);
    }//GEN-LAST:event_myIPFocusLost

    private void netmaskIPActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_netmaskIPActionPerformed
    {//GEN-HEADEREND:event_netmaskIPActionPerformed
        setIpAddr(netmaskIP, "1720");
    }//GEN-LAST:event_netmaskIPActionPerformed

    private void netmaskIPFocusLost(java.awt.event.FocusEvent evt)//GEN-FIRST:event_netmaskIPFocusLost
    {//GEN-HEADEREND:event_netmaskIPFocusLost
        netmaskIPActionPerformed(null);
    }//GEN-LAST:event_netmaskIPFocusLost

    private void gwIPActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_gwIPActionPerformed
    {//GEN-HEADEREND:event_gwIPActionPerformed
        setIpAddr(gwIP, "1730");
    }//GEN-LAST:event_gwIPActionPerformed

    private void gwIPFocusLost(java.awt.event.FocusEvent evt)//GEN-FIRST:event_gwIPFocusLost
    {//GEN-HEADEREND:event_gwIPFocusLost
        gwIPActionPerformed(null);
    }//GEN-LAST:event_gwIPFocusLost

    private void DNS_IP1ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_DNS_IP1ActionPerformed
    {//GEN-HEADEREND:event_DNS_IP1ActionPerformed
        setIpAddr(DNS_IP1, "1740");
    }//GEN-LAST:event_DNS_IP1ActionPerformed

    private void DNS_IP1FocusLost(java.awt.event.FocusEvent evt)//GEN-FIRST:event_DNS_IP1FocusLost
    {//GEN-HEADEREND:event_DNS_IP1FocusLost
        DNS_IP1ActionPerformed(null);
    }//GEN-LAST:event_DNS_IP1FocusLost

    private void DNS_IP2ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_DNS_IP2ActionPerformed
    {//GEN-HEADEREND:event_DNS_IP2ActionPerformed
        setIpAddr(DNS_IP2, "1750");
    }//GEN-LAST:event_DNS_IP2ActionPerformed

    private void DNS_IP2FocusLost(java.awt.event.FocusEvent evt)//GEN-FIRST:event_DNS_IP2FocusLost
    {//GEN-HEADEREND:event_DNS_IP2FocusLost
        DNS_IP2ActionPerformed(null);
    }//GEN-LAST:event_DNS_IP2FocusLost

    private void NTP_IPActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_NTP_IPActionPerformed
    {//GEN-HEADEREND:event_NTP_IPActionPerformed
        setIpAddr(NTP_IP, "1760");
    }//GEN-LAST:event_NTP_IPActionPerformed

    private void NTP_IPFocusLost(java.awt.event.FocusEvent evt)//GEN-FIRST:event_NTP_IPFocusLost
    {//GEN-HEADEREND:event_NTP_IPFocusLost
        NTP_IPActionPerformed(null);
    }//GEN-LAST:event_NTP_IPFocusLost

    private void enableNTPActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_enableNTPActionPerformed
    {//GEN-HEADEREND:event_enableNTPActionPerformed
        setCheckBox(enableNTP, "1770");
    }//GEN-LAST:event_enableNTPActionPerformed

    private void useOnlyTenMBitActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_useOnlyTenMBitActionPerformed
    {//GEN-HEADEREND:event_useOnlyTenMBitActionPerformed
        setCheckBox(useOnlyTenMBit, "1780");
    }//GEN-LAST:event_useOnlyTenMBitActionPerformed

    private void useAltDNSActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_useAltDNSActionPerformed
    {//GEN-HEADEREND:event_useAltDNSActionPerformed
        setCheckBox(useAltDNS, "1790");
    }//GEN-LAST:event_useAltDNSActionPerformed

    
    
    void initTableListener()
    {
        
    
   
    
      
    }
            
    class Task extends SwingWorker<Void, Void> {
        
        int progress = 0;
        boolean errorOccured = false;
        
        void progIncr()
        {
            progress += 1;
            setProgress(progress);
        }
        /*
         * Main task. Executed in background thread.
         */
        @Override
        public Void doInBackground()
        {
            
            
            progIncr();
            
            try
            {
                firmwareVersion = snmp.snmpGetInteger("160");
            }
            catch (Exception e)
            {
                System.out.println("could not read firmware version");
            }
            
            
            try
            {
                remoteDisplay.updatePixel(snmp.snmpGetBinaryString(
                    remoteDisplayScreens[0]));
                progIncr();                   

                myIP.setIpAddress(InetAddress.getByAddress(
                        snmp.snmpGetBinaryString("1710")));
                progIncr(); 

                netmaskIP.setIpAddress(InetAddress.getByAddress(
                        snmp.snmpGetBinaryString("1720")));
                progIncr(); 

                gwIP.setIpAddress(InetAddress.getByAddress(
                        snmp.snmpGetBinaryString("1730")));
                progIncr(); 

                DNS_IP1.setIpAddress(InetAddress.getByAddress(
                        snmp.snmpGetBinaryString("1740")));
                progIncr(); 

                DNS_IP2.setIpAddress(InetAddress.getByAddress(
                        snmp.snmpGetBinaryString("1750")));
                progIncr(); 

                NTP_IP.setIpAddress(InetAddress.getByAddress(
                        snmp.snmpGetBinaryString("1760")));
                progIncr(); 
                
                enableNTP.setSelected(snmp.snmpGetInteger("1770") == 1);
                progIncr();

                useOnlyTenMBit.setSelected(snmp.snmpGetInteger("1780") == 1);
                progIncr();

                useAltDNS.setSelected(snmp.snmpGetInteger("1790") == 1);
                progIncr();
            }
            catch (Exception e)
            {
                System.out.println("could not read special variables");
            }
            
               
            
            progIncr();
            try
            {
                
                callSign.setText( snmp.snmpGetString("30"));
                progIncr();
                
                callSignExt.setText(snmp.snmpGetString("750"));
                progIncr();
                
                rptDirectCheckbox.setSelected(snmp.snmpGetInteger("760") == 1);
                rptSpinner.setEnabled(! rptDirectCheckbox.isSelected());
                // rptTable.setEnabled(! rptDirectCheckbox.isSelected());
                progIncr();
              
                inputVoltage.setText( String.format("%10.2fV", (snmp.snmpGetInteger("40") * 0.001) ));
                progIncr();
                
                phyRxInv.setSelected(snmp.snmpGetInteger("250") == 1);
                progIncr();
                
                phySoftwareVersion.setText ( snmp.snmpGetString("210") );
                progIncr();
                
                int i;
                byte[] phyCPUID = snmp.snmpGetBinaryString("220");
                
                if (phyCPUID.length == 15)
                {
                    StringBuilder s = new StringBuilder();
                    
                    for (i=0; i < 15; i++)
                    {
                        if (i > 0)
                        {
                            s.append('-');
                        }
                        s.append(String.format("%02x", ((int) phyCPUID[i]) & 0xFF));                        
                    }
                    
                    phySerialNumTextField.setText(s.toString());
                }
                else
                {
                    phySerialNumTextField.setText("N/A");
                }
                
                phyTxDelay.setText( Integer.toString( snmp.snmpGetInteger("230") ));
                progIncr();
                
                phyTxGain.setText( Integer.toString( snmp.snmpGetInteger("240") ));
                progIncr();
                
                phyTxDcShift.setText( Integer.toString( snmp.snmpGetInteger("260") ));
                progIncr();
                
                pttBeepDuration.setText( Integer.toString( snmp.snmpGetInteger("640") ));
                progIncr();
                
                pttBeepFrequency.setText( Integer.toString( snmp.snmpGetInteger("650") ));
                progIncr();
                
                pttBeepVolume.setText( Integer.toString( snmp.snmpGetInteger("660") ));
                progIncr();
                
                standbyBeepDuration.setText( Integer.toString( snmp.snmpGetInteger("610") ));
                progIncr();
                
                standbyBeepFrequency.setText( Integer.toString( snmp.snmpGetInteger("620") ));
                progIncr();
                
                standbyBeepVolume.setText( Integer.toString( snmp.snmpGetInteger("630") ));
                progIncr();
                
                rptSpinner.setValue(snmp.snmpGetInteger("710"));
                progIncr();
                
                phyRxDevFactor.setText( Integer.toString( snmp.snmpGetInteger("290") ));
                progIncr();
                
                yourCallSpinner.setValue(snmp.snmpGetInteger("730"));
                progIncr();
                
                backlightSlider.setValue(snmp.snmpGetInteger("920"));
                progIncr();
                
                contrastSlider.setValue(snmp.snmpGetInteger("910"));
                progIncr();
                
                txmsgTextField.setText(snmp.snmpGetString("770"));
                progIncr();
                
                dprsEnableCheckBox.setSelected(snmp.snmpGetInteger("810") == 1);
                progIncr();
                
                dprsSymbolComboBox.setSelectedIndex(snmp.snmpGetInteger("820"));
                progIncr();
                
                dprsTextField.setText(snmp.snmpGetString("830"));
                progIncr();
                
                
                TableModel tm = rptTable.getModel();
               
                
                // tm.removeTableModelListener( rptTableListener  );
                
                for(i=0; i < 5; i++)
                {
                    tm.setValueAt( snmp.snmpGetString("7212" + UP4DAR_SNMP.getOIDChar(i+1)), i, 1);
                    tm.setValueAt( snmp.snmpGetString("7213" + UP4DAR_SNMP.getOIDChar(i+1)), i, 2);
                    progIncr();
                }
                
                tm.addTableModelListener( new TableModelListener() {

                    boolean ignoreEvents = false;
                   
                    @Override
                    public void tableChanged(TableModelEvent tme) {
                        int col = tme.getColumn();
                        
                        int row = tme.getFirstRow();
                        
                        // System.out.println("test");
                        
                        if (!ignoreEvents)
                        {
                            try
                            {                       
                                snmp.snmpSetString("721" + UP4DAR_SNMP.getOIDChar(col+1) + 
                                        UP4DAR_SNMP.getOIDChar(row+1), 
                                        (String) rptTable.getModel().getValueAt(row, col) );
                                
                                ignoreEvents = true;
                                rptTable.getModel().setValueAt( snmp.snmpGetString("721" +
                                        (col+1) + "" + (row+1)), row, col);

                            } catch (Exception ex)
                            {
                                Logger.getLogger(UP4DAR_Configurator.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            ignoreEvents = false;
                        }
                     }
                    }  );
                
                tm = yourCallTable.getModel();
                
                // tm.removeTableModelListener( yourCallTableListener  );
                
                for(i=0; i < 10; i++)
                {
                    tm.setValueAt( "" + (i+1), i, 0 );
                    tm.setValueAt( snmp.snmpGetString("7412" + 
                            UP4DAR_SNMP.getOIDChar(i+1)), i, 1);
                    progIncr();
                }
                
                tm.addTableModelListener( new TableModelListener() {

                    boolean ignoreEvents = false;
                   
                    @Override
                    public void tableChanged(TableModelEvent tme) {
                        int col = tme.getColumn();
                        
                        int row = tme.getFirstRow();
                        
                        if (!ignoreEvents)
                        {
                            try
                            {                       
                                snmp.snmpSetString("741" + UP4DAR_SNMP.getOIDChar(col+1) + 
                                        UP4DAR_SNMP.getOIDChar(row+1), 
                                        (String) yourCallTable.getModel().getValueAt(row, col) );

                                ignoreEvents = true;
                                yourCallTable.getModel().setValueAt( snmp.snmpGetString("741" +
                                        (col+1) + "" + (row+1)), row, col);

                            } catch (Exception ex)
                            {
                                Logger.getLogger(UP4DAR_Configurator.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            ignoreEvents = false;
                        }
                     }
                    }  );
                
                
            } catch (Exception ex)
            {
                Logger.getLogger(UP4DAR_Configurator.class.getName()).log(Level.SEVERE, null, ex);
                errorOccured = true;
            }
            
                       
           
            return null;
        }

        /*
         * Executed in event dispatching thread
         */
        @Override
        public void done()
        {
            // setCursor(null); //turn off the wait cursor
            loadingFrame.setVisible(false);
            if (!errorOccured)
            {
                configFrame.setVisible(true);
                
                updateMenuItem.setEnabled(true);
                
            }
            else
            {
                connectButton.setEnabled(true);
            }
        }
    }
    
    
    
    
    Task task;

    /*
    @Override
    public void propertyChange(PropertyChangeEvent evt)
    {
        if (evt.getPropertyName().equals("progress"))
        {
            int progress = (Integer) evt.getNewValue();
            loadProgressBar.setValue(progress);
        } 
    }
    */
    
    
    void doConnect ( InetAddress a, String cmnty ) throws Exception
    {
        // System.out.println(a);
        
        
        
        snmp = new UP4DAR_SNMP(a, cmnty);
        
        String h = snmp.snmpConnect();
        
        snmpCmnty.setText(cmnty);
       
        loadProgressBar.setValue(0);
        loadProgressLabel.setText("Loading data...");
        
        loadingFrame.setVisible(true);
        
       
        

        // setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
      
        task = new Task();
        task.addPropertyChangeListener( new PropertyChangeListener() {

            @Override
            public void propertyChange(PropertyChangeEvent pce)
            {
                if (pce.getPropertyName().equals("progress"))
                {
                    int progress = (Integer) pce.getNewValue();
                    loadProgressBar.setValue(progress);
                } 
            }
        });
        task.execute();
            
       
    }
    
    
    
    public static InetAddress cmdlineIP = null;
    public static String cmdlineCmnty = null;
    
    public static int snmpPortNumber = 161;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UP4DAR_Configurator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UP4DAR_Configurator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UP4DAR_Configurator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UP4DAR_Configurator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        if (args.length >= 2) // minimum two arguments
        {
            try
            {
                cmdlineIP = InetAddress.getByName(args[0]);
                cmdlineCmnty = args[1];
            }
            catch (java.net.UnknownHostException e)
            {
                cmdlineIP = null;
            }
        }
        
        if (args.length >= 3) // minimum three arguments
        {
            try
            {
                snmpPortNumber = Integer.parseInt(args[2]) & 0xFFFF;
            }
            catch (NumberFormatException e)
            {                
            }
        }
        
        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new UP4DAR_Configurator().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    up4dar_configurator.JIpTextField DNS_IP1;
    up4dar_configurator.JIpTextField DNS_IP2;
    javax.swing.JButton ExitButton;
    up4dar_configurator.JIpTextField NTP_IP;
    javax.swing.JMenuItem aboutMenuItem;
    javax.swing.JPanel audioPanel;
    javax.swing.JSlider backlightSlider;
    javax.swing.JList boardList;
    javax.swing.JScrollPane boardListScrollPane;
    javax.swing.JTextField callSign;
    javax.swing.JTextField callSignExt;
    javax.swing.JPanel callsignPanel;
    javax.swing.JInternalFrame configFrame;
    javax.swing.JButton connectButton;
    javax.swing.JMenuItem contentMenuItem;
    javax.swing.JSlider contrastSlider;
    javax.swing.JMenuItem copyMenuItem;
    javax.swing.JMenuItem cutMenuItem;
    javax.swing.JPanel debugPanel;
    javax.swing.JMenuItem deleteMenuItem;
    javax.swing.JDesktopPane desktopPane;
    javax.swing.JPanel displayPanel;
    javax.swing.JCheckBox dprsEnableCheckBox;
    javax.swing.JPanel dprsPanel;
    javax.swing.JComboBox dprsSymbolComboBox;
    javax.swing.JTextField dprsTextField;
    javax.swing.JPanel dvPanel;
    javax.swing.JMenu editMenu;
    javax.swing.JCheckBox enableNTP;
    javax.swing.JMenuItem exitMenuItem;
    javax.swing.JMenu fileMenu;
    up4dar_configurator.JIpTextField gwIP;
    javax.swing.JMenu helpMenu;
    javax.swing.JLabel inputVoltage;
    javax.swing.JButton jButton1;
    javax.swing.JButton jButton2;
    javax.swing.JButton jButton3;
    javax.swing.JButton jButton4;
    javax.swing.JButton jButton5;
    javax.swing.JButton jButton6;
    javax.swing.JLabel jLabel1;
    javax.swing.JLabel jLabel10;
    javax.swing.JLabel jLabel11;
    javax.swing.JLabel jLabel12;
    javax.swing.JLabel jLabel13;
    javax.swing.JLabel jLabel14;
    javax.swing.JLabel jLabel15;
    javax.swing.JLabel jLabel16;
    javax.swing.JLabel jLabel17;
    javax.swing.JLabel jLabel18;
    javax.swing.JLabel jLabel19;
    javax.swing.JLabel jLabel2;
    javax.swing.JLabel jLabel20;
    javax.swing.JLabel jLabel21;
    javax.swing.JLabel jLabel22;
    javax.swing.JLabel jLabel23;
    javax.swing.JLabel jLabel24;
    javax.swing.JLabel jLabel25;
    javax.swing.JLabel jLabel26;
    javax.swing.JLabel jLabel27;
    javax.swing.JLabel jLabel28;
    javax.swing.JLabel jLabel29;
    javax.swing.JLabel jLabel3;
    javax.swing.JLabel jLabel30;
    javax.swing.JLabel jLabel4;
    javax.swing.JLabel jLabel5;
    javax.swing.JLabel jLabel6;
    javax.swing.JLabel jLabel7;
    javax.swing.JLabel jLabel8;
    javax.swing.JLabel jLabel9;
    javax.swing.JPanel jPanel4;
    javax.swing.JPanel jPanel5;
    javax.swing.JScrollPane jScrollPane1;
    javax.swing.JScrollPane jScrollPane2;
    javax.swing.JTabbedPane jTabbedPane1;
    javax.swing.JButton loadCancelButton;
    javax.swing.JProgressBar loadProgressBar;
    javax.swing.JLabel loadProgressLabel;
    javax.swing.JInternalFrame loadingFrame;
    javax.swing.JMenuBar menuBar;
    up4dar_configurator.JIpTextField myIP;
    up4dar_configurator.JIpTextField netmaskIP;
    javax.swing.JInternalFrame networkListFrame;
    javax.swing.JPanel networkPanel;
    javax.swing.JMenuItem openMenuItem;
    javax.swing.JMenuItem pasteMenuItem;
    javax.swing.JPanel phyPanel;
    javax.swing.JTextField phyRxDevFactor;
    javax.swing.JCheckBox phyRxInv;
    javax.swing.JTextField phySerialNumTextField;
    javax.swing.JLabel phySoftwareVersion;
    javax.swing.JTextField phyTxDcShift;
    javax.swing.JTextField phyTxDelay;
    javax.swing.JTextField phyTxGain;
    javax.swing.JTextField pttBeepDuration;
    javax.swing.JTextField pttBeepFrequency;
    javax.swing.JTextField pttBeepVolume;
    javax.swing.JLabel remoteDisplayLabel;
    javax.swing.JComboBox remoteScreenComboBox;
    javax.swing.JSlider remoteScreenRefreshRateSlider;
    javax.swing.JCheckBox rptDirectCheckbox;
    javax.swing.JPanel rptSettingsPanel;
    javax.swing.JSpinner rptSpinner;
    javax.swing.JTable rptTable;
    javax.swing.JMenuItem saveAsMenuItem;
    javax.swing.JMenuItem saveMenuItem;
    javax.swing.JButton saveToFlash;
    javax.swing.JTextField snmpCmnty;
    javax.swing.JTextField standbyBeepDuration;
    javax.swing.JTextField standbyBeepFrequency;
    javax.swing.JTextField standbyBeepVolume;
    javax.swing.JTextField txmsgTextField;
    javax.swing.JFileChooser updateFileChooser;
    javax.swing.JInternalFrame updateFrame;
    javax.swing.JLabel updateLabel1;
    javax.swing.JLabel updateLabel2;
    javax.swing.JMenuItem updateMenuItem;
    javax.swing.JButton updateOKButton;
    javax.swing.JProgressBar updateProgressBar;
    javax.swing.JCheckBox useAltDNS;
    javax.swing.JCheckBox useOnlyTenMBit;
    javax.swing.JPanel yourCallSettingsPanel;
    javax.swing.JSpinner yourCallSpinner;
    javax.swing.JTable yourCallTable;
    // End of variables declaration//GEN-END:variables

    
}
