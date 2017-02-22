package pl.dmcs.dszubertClient;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import pl.dmcs.dszubertServer.StudentLotteryService;

import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
public class GUI {

	protected Shell shell;
	private Text text_1;
	private Text text_2;
	private Text text;
	Button button;
	Button button_0;
	Button button_1;
	Button button_2;
	Button button_3;
	Button button_4;
	Button button_5;
	Button button_6;
	Button button_7;
	String[] mecz = new String[9];
	String sign = new String();
	String username = new String();
	int tmp = 123;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			GUI window = new GUI();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
		final StudentLotteryService studentLotteryService = (StudentLotteryService)context.getBean("studentLotteryService");
		
		Display display = Display.getDefault();
		createContents(studentLotteryService);
		shell.open();
		
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 * @param studentLotteryService 
	 * @return 
	 */
	protected void createContents(final StudentLotteryService studentLotteryService) {
		
		
		shell = new Shell();
		shell.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		shell.setSize(464, 553);
		shell.setText("Kolko krzyzyk");
		
		
		final Thread updateThread = new Thread() {
	        public void run() {
	            while (true) {
	                Display.getDefault().syncExec(new Runnable() {
	                	
	                    @Override
	                    public void run() {
	                    	
            				String users = "";
            				users = studentLotteryService.getMatchStatus(users);
            				if(!text_2.getText().equals(users)) {
            					text_2.setText(users);
            					text_2.setSelection(text_2.getText().length());
            				}
            				if(studentLotteryService.getMatch()!=mecz)
            				mecz = studentLotteryService.getMatch();
            				
            				if ((sign.equals("x") && studentLotteryService.getTurn()==0) || (sign.equals("o") && studentLotteryService.getTurn()==1))
            					enableButtons();
            				
            				if ((sign.equals("x") && studentLotteryService.checkIfEnd()==0) || (sign.equals("o") && studentLotteryService.checkIfEnd()==1)){
            					enableButtons();
            					studentLotteryService.resetMap();
            				}
            					
	                		setButtons();
	                    }
	                });

	                try {
	                    Thread.sleep(500);
	                } catch (InterruptedException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
	    };
	    // background thread
	    //updateThread.setDaemon(true);
	    
		
		text_1 = new Text(shell, SWT.BORDER);
		text_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		text_1.setBounds(10, 29, 327, 21);
		
		Label lblNickname = new Label(shell, SWT.NONE);
		lblNickname.setBounds(10, 8, 55, 15);
		lblNickname.setText("Nickname");
		
		text_2 = new Text(shell, SWT.READ_ONLY | SWT.WRAP | SWT.V_SCROLL | SWT.MULTI);
		text_2.setEditable(false);
		text_2.setSelection(text_2.getText().length());
		text_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		text_2.setBounds(10, 56, 428, 41);

		
		final Button btnSave = new Button(shell, SWT.NONE);
		btnSave.setBounds(363, 27, 75, 25);
		btnSave.setText("Start");
		
		shell.addListener(SWT.Close, new Listener() {
		      public void handleEvent(Event e) {
		    	  if(btnSave.isEnabled()==false)
		    		  studentLotteryService.removeUser(text_1.getText());
		      }
		});
		
		button_0 = new Button(shell, SWT.NONE);
		button_0.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				mecz[0]=sign;
				studentLotteryService.setMatch(mecz);
				if(sign.equals("x"))
					studentLotteryService.setTurn(1);
				else
					studentLotteryService.setTurn(0);
				disableButtons();
			}
		});
		button_0.setBounds(10, 143, 122, 106);
		
		button = new Button(shell, SWT.NONE);
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				mecz[1]=sign;
				studentLotteryService.setMatch(mecz);
				if(sign.equals("x"))
					studentLotteryService.setTurn(1);
				else
					studentLotteryService.setTurn(0);
				disableButtons();
			}
		});
		button.setBounds(154, 143, 122, 106);
		
		button_1 = new Button(shell, SWT.NONE);
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				mecz[2]=sign;
				studentLotteryService.setMatch(mecz);
				if(sign.equals("x"))
					studentLotteryService.setTurn(1);
				else
					studentLotteryService.setTurn(0);
				disableButtons();
			}
		});
		button_1.setBounds(298, 143, 122, 106);
		
		button_2 = new Button(shell, SWT.NONE);
		button_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				mecz[3]=sign;
				studentLotteryService.setMatch(mecz);
				if(sign.equals("x"))
					studentLotteryService.setTurn(1);
				else
					studentLotteryService.setTurn(0);
				disableButtons();
			}
		});
		button_2.setBounds(10, 265, 122, 106);
		
		button_3 = new Button(shell, SWT.NONE);
		button_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				mecz[4]=sign;
				studentLotteryService.setMatch(mecz);
				if(sign.equals("x"))
					studentLotteryService.setTurn(1);
				else
					studentLotteryService.setTurn(0);
				disableButtons();
			}
		});
		button_3.setBounds(154, 265, 122, 106);
		
		button_4 = new Button(shell, SWT.NONE);
		button_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				mecz[5]=sign;
				studentLotteryService.setMatch(mecz);
				if(sign.equals("x"))
					studentLotteryService.setTurn(1);
				else
					studentLotteryService.setTurn(0);
				disableButtons();
			}
		});
		button_4.setBounds(298, 265, 122, 106);
		
		button_5 = new Button(shell, SWT.NONE);
		button_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				mecz[6]=sign;
				studentLotteryService.setMatch(mecz);
				if(sign.equals("x"))
					studentLotteryService.setTurn(1);
				else
					studentLotteryService.setTurn(0);
				disableButtons();
			}
		});
		button_5.setBounds(10, 390, 122, 106);
		
		button_6 = new Button(shell, SWT.NONE);
		button_6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				mecz[7]=sign;
				studentLotteryService.setMatch(mecz);
				if(sign.equals("x"))
					studentLotteryService.setTurn(1);
				else
					studentLotteryService.setTurn(0);
				disableButtons();
			}
		});
		button_6.setBounds(154, 390, 122, 106);
		
		button_7 = new Button(shell, SWT.NONE);
		button_7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				mecz[8]=sign;
				studentLotteryService.setMatch(mecz);
				if(sign.equals("x"))
					studentLotteryService.setTurn(1);
				else
					studentLotteryService.setTurn(0);
				disableButtons();
			}
		});
		button_7.setBounds(298, 388, 122, 106);
		
		Label lblOponent = new Label(shell, SWT.NONE);
		lblOponent.setText("Sign");
		lblOponent.setBounds(10, 103, 55, 15);
		
		text = new Text(shell, SWT.BORDER);
		text.setBounds(86, 103, 148, 21);
		
		btnSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				int i = new Integer(0);
				String message = new String(text_1.getText());
				i = studentLotteryService.SendNewMessageToServer(message);
				if (i==1) {
					btnSave.setEnabled(false);
					text_1.setEnabled(false);
					username = text_1.getText();
					sign=studentLotteryService.getSign(message);
					text.setText(sign);
					updateThread.start();
				}
			}
		});
	    
	}
	public void setButtons() {
		button_0.setText(mecz[0]);
		button.setText(mecz[1]);
		button_1.setText(mecz[2]);
		button_2.setText(mecz[3]);
		button_3.setText(mecz[4]);
		button_4.setText(mecz[5]);
		button_5.setText(mecz[6]);
		button_6.setText(mecz[7]);
		//button_7.setText(mecz[8]);
	}
	
	public void disableButtons(){
		button_0.setEnabled(false);
		button.setEnabled(false);
		button_1.setEnabled(false);
		button_2.setEnabled(false);
		button_3.setEnabled(false);
		button_4.setEnabled(false);
		button_5.setEnabled(false);
		button_6.setEnabled(false);
		button_7.setEnabled(false);
	}
	
	public void enableButtons(){
		button_0.setEnabled(true);
		button.setEnabled(true);
		button_1.setEnabled(true);
		button_2.setEnabled(true);
		button_3.setEnabled(true);
		button_4.setEnabled(true);
		button_5.setEnabled(true);
		button_6.setEnabled(true);
		button_7.setEnabled(true);
	}
}


