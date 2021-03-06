package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;

import java.awt.Color;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.JSeparator;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ListSelectionModel;

import network.Client;

import model.Drink.*;

public class ManagerView extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JPanel toolBarPanel;
	private static JTable table;
	private JMenuItem mntmExit;
	private JMenuItem mntmAbout;
	private JButton btnAddDrink;
	private Client client;
	private LoginView lv;
	private JButton btnRemoveDrink;
	private JButton btnModifyDrink;
	private JButton btnViewOrders;
	private JMenuItem mntmLogout;
	private JScrollPane scrollPane;
	private static JTable orderTable;
	private final Client closeClient;

	/**
	 * Create the frame.
	 */
	public ManagerView(Client client, LoginView lv) {
		this.closeClient = client;
		this.client = client;
		this.lv = lv;
		
		setResizable(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(ManagerView.class.getResource("/resources/drink.png")));
		setTitle("AP-Project v0.1.1");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	
		setBounds(100, 100, 720, 576);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent aEvent) {
				closeClient.sendChoice("exit");
				System.exit(EXIT_ON_CLOSE);
			}
		});
		
		mntmLogout = new JMenuItem("Logout");
		mntmLogout.addActionListener(this);
		mntmLogout.setIcon(new ImageIcon(ManagerView.class.getResource("/resources/logout.png")));
		mnFile.add(mntmLogout);
		mntmExit.setIcon(new ImageIcon(ManagerView.class.getResource("/resources/exit.png")));
		mnFile.add(mntmExit);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		mntmAbout = new JMenuItem("About");
		mntmAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				About abt = new About();
				
				JOptionPane.showMessageDialog(ManagerView.this, abt.getEditorPane(),"About AP-Project", JOptionPane.PLAIN_MESSAGE);
			}
		});
		mntmAbout.setIcon(new ImageIcon(ManagerView.class.getResource("/resources/about.png")));
		mnHelp.add(mntmAbout);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(46, 139, 87));
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		
		toolBarPanel = new JPanel();
		toolBarPanel.setBackground(new Color(139, 0, 0));
		
		scrollPane = new JScrollPane();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(toolBarPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 714, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(toolBarPanel, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 515, GroupLayout.PREFERRED_SIZE))
		);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setFocusable(false);
		toolBar.setFloatable(false);
		toolBar.setBackground(new Color(139, 0, 0));
		GroupLayout gl_toolBarPanel = new GroupLayout(toolBarPanel);
		gl_toolBarPanel.setHorizontalGroup(
			gl_toolBarPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_toolBarPanel.createSequentialGroup()
					.addGap(68)
					.addComponent(toolBar, GroupLayout.PREFERRED_SIZE, 543, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(103, Short.MAX_VALUE))
		);
		gl_toolBarPanel.setVerticalGroup(
			gl_toolBarPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_toolBarPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(toolBar, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
					.addGap(6))
		);
		
		 btnAddDrink = new JButton("Add Drink ");
		btnAddDrink.setFocusable(false);
		btnAddDrink.setForeground(new Color(255, 255, 255));
		btnAddDrink.setBackground(new Color(139, 0, 0));
		btnAddDrink.addActionListener(this);
		btnAddDrink.setIcon(new ImageIcon(ManagerView.class.getResource("/resources/add_drink.png")));
		toolBar.add(btnAddDrink);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(139, 0, 0));
		separator.setBackground(new Color(139, 0, 0));
		toolBar.add(separator);
		
		btnRemoveDrink = new JButton("Remove Drink");
		btnRemoveDrink.setFocusable(false);
		btnRemoveDrink.setForeground(new Color(255, 255, 255));
		btnRemoveDrink.setBackground(new Color(139, 0, 0));
		btnRemoveDrink.addActionListener(this);
		btnRemoveDrink.setIcon(new ImageIcon(ManagerView.class.getResource("/resources/rem_drink.png")));
		toolBar.add(btnRemoveDrink);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBackground(new Color(139, 0, 0));
		separator_1.setForeground(new Color(139, 0, 0));
		toolBar.add(separator_1);
		
		btnModifyDrink = new JButton("Modify Drink ");
		btnModifyDrink.setFocusable(false);
		btnModifyDrink.setForeground(new Color(255, 255, 255));
		btnModifyDrink.setBackground(new Color(139, 0, 0));
		btnModifyDrink.addActionListener(this);
		btnModifyDrink.setIcon(new ImageIcon(ManagerView.class.getResource("/resources/mod_drink.png")));
		toolBar.add(btnModifyDrink);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(new Color(139, 0, 0));
		separator_2.setBackground(new Color(139, 0, 0));
		toolBar.add(separator_2);
		
		btnViewOrders = new JButton("View Orders ");
		btnViewOrders.setFocusable(false);
		btnViewOrders.setIcon(new ImageIcon(ManagerView.class.getResource("/resources/orders.png")));
		btnViewOrders.setForeground(new Color(255, 255, 255));
		btnViewOrders.setBackground(new Color(139, 0, 0));
		btnViewOrders.addActionListener(this);
		toolBar.add(btnViewOrders);
		toolBarPanel.setLayout(gl_toolBarPanel);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setSelectionBackground(new Color(46, 139, 87));
		table.setRowMargin(0);
		table.setFont(new Font("Serif", Font.PLAIN, 12));
		table.setRowHeight(27);
		table.setGridColor(new Color(0, 0, 0));
		table.setBackground(new Color(211, 211, 211));
		client.sendChoice("drink table");
		//boolean b = client.recieveResponse();
		
		table.setModel(client.recieveTableModel());
		
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
		
//		orderTable = new JTable();
//		orderTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//		orderTable.setSelectionBackground(new Color(46, 139, 87));
//		orderTable.setRowMargin(0);
//		orderTable.setFont(new Font("Serif", Font.PLAIN, 12));
//		orderTable.setRowHeight(27);
//		orderTable.setGridColor(new Color(0, 0, 0));
//		orderTable.setBackground(new Color(211, 211, 211));
//		client.sendChoice("order table");
//		//boolean b = client.recieveResponse();
//		orderTable.setModel(client.recieveTableModel());
		
	//	scrollPane.setViewportView(orderTable);
		WindowListener closeListener = new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e){
				this.closeWindow();
			}
			
			public void closeWindow(){
				closeClient.sendChoice("exit");
				System.exit(EXIT_ON_CLOSE);
			}
		};
		
		this.addWindowListener(closeListener);
	}
	
	public static JTable getTable(){
		return table;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {  
		
		if( e.getSource() == btnAddDrink ){			//if add drink button is pressed
			client.sendChoice("drink table");
			table.setModel(client.recieveTableModel());
			AddDrinkView addDrink = new AddDrinkView(client);
			addDrink.show();
		}
		
		else if(e.getSource() == btnViewOrders){			// if view orders button is pressed
			client.sendChoice("order table");
			table.setModel(client.recieveTableModel());		
			
		}
		
		else if(e.getSource() == mntmLogout){			// if logout button is pressed
			lv.setVisible(true);
			dispose();
		}
		else if(e.getSource() == btnModifyDrink){		// if modify drink button is pressed
			ChangeDrinkView cv = new ChangeDrinkView(client);
			cv.show();
		}
		else if(e.getSource() == btnRemoveDrink){
			System.out.println("remove drink pressed");
			deleteDrinkView dv = new deleteDrinkView(client);
			dv.show();
		}
	}
}
