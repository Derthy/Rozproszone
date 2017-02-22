package pl.dmcs.dszubertClient;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import pl.dmcs.dszubertServer.StudentLotteryService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
public class GUI {

	protected Shell shell;
	private Text text;
	private Text text_1;
	private Text text_2;

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
		
		Thread updateThread = new Thread() {
	        public void run() {
	            while (true) {
	                Display.getDefault().syncExec(new Runnable() {
	                	
	                    @Override
	                    public void run() {
	                    	
            				String newMessages = "";
            				newMessages = studentLotteryService.getNewMessagesFromServer(newMessages);
            				if(!text_2.getText().equals(newMessages)) {
            					text_2.setText(newMessages);
            					text_2.setSelection(text_2.getText().length());
            				}
	                		
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
	    updateThread.setDaemon(true);
	    updateThread.start();
		
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
		shell.setSize(464, 509);
		shell.setText("SWT Application");
		
		text = new Text(shell, SWT.BORDER | SWT.MULTI);
		text.setEnabled(false);
		text.setBounds(10, 323, 428, 86);
		
		text_1 = new Text(shell, SWT.BORDER);
		text_1.setBounds(10, 29, 327, 21);
		
		Label lblNickname = new Label(shell, SWT.NONE);
		lblNickname.setBounds(10, 8, 55, 15);
		lblNickname.setText("Nickname");
		
		text_2 = new Text(shell, SWT.READ_ONLY | SWT.WRAP | SWT.V_SCROLL | SWT.MULTI);
		text_2.setEditable(false);
		text_2.setSelection(text_2.getText().length());
		text_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		text_2.setBounds(10, 71, 428, 246);
		
		final Button btnSend = new Button(shell, SWT.NONE);
		btnSend.setEnabled(false);
		btnSend.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
				Date today = Calendar.getInstance().getTime(); 
				String reportDate = df.format(today);
				if(!text.getText().equals("")){
					String message = new String("[ " + reportDate + " ] - " + text_1.getText() + " : " + text.getText());
					studentLotteryService.SendNewMessageToServer(message);
					text.setText("");
				}
			}
		});
		btnSend.setBounds(363, 425, 75, 25);
		btnSend.setText("Send");

		
		final Button btnSave = new Button(shell, SWT.NONE);
		btnSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
				Date today = Calendar.getInstance().getTime(); 
				String reportDate = df.format(today);
				text_1.setEnabled(false);
				text.setEnabled(true);
				btnSend.setEnabled(true);
				btnSend.setGrayed(false);
				btnSave.setEnabled(false);
				String message = new String("[ " + reportDate + " ] - " + text_1.getText() + " : " + "Joined the channel");
				studentLotteryService.SendNewMessageToServer(message);
			}
		});
		btnSave.setBounds(363, 27, 75, 25);
		btnSave.setText("Save");

	}
	

}
