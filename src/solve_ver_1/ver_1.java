package solve_ver_1;
import java.awt.*;

import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
class point extends JFrame{
	private static final long serialVersionUID = 1L;
	private final String[] word = {"����","���"};                             							//������ť�ı�
	private final JTextField jtf[] = new JTextField[4];													//4����ʾ����
	private final JTextArea jta = new JTextArea();                   	 								//1�����
	private final JScrollPane js = new JScrollPane();                       							//������
	private final JButton jb[] = new JButton[word.length];                  							//������ť
	private final String match = "[1-9]?|1[0-3]";														//jtƥ�䴮
	private Random r = new Random();																	//1-13�������
	private final Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();     				//��ȡ��Ļ
	public point() {
		setTitle("24��");																				//��Ļ����
		setLayout(null);																				//���Բ���
		New_JTextField(jtf);																			//�����ı������
		New_JButton(jb);																				//���ɰ�ť����
		Bind_js_area(js,jta);																			//�󶨻�����������ı���
		Button_Actionlistener();																		//��ť������
		Add();																							//������
		setBounds((int)(screensize.getWidth()/2-300),(int)(screensize.getHeight()/2-200),600,400);		//��ĻĬ����ʾ����ʾ������
		setResizable(false);																			//���ڴ�С���ɸı�
		getContentPane().setBackground(Color.white);													//�׵�
		setVisible(true);																				//�ɼ�
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);										//���̹ر�
	}
	private void New_JTextField(final JTextField[] jtf) {
		for (int i = 0; i < jtf.length; i++) {															//ѭ�������ı���
			jtf[i] = new JTextField();																	//��������
			jtf[i].setHorizontalAlignment(JTextField.CENTER);											//���־���
			jtf[i].setFont(new Font("����",Font.BOLD,55));												//��������
			jtf[i].setBounds(126*i+61, 40, 100, 100);													//�����ı���λ��
			jtf[i].addKeyListener(new KeyAdapter() {													//���������
				public void keyTyped(KeyEvent e) {
					char code = e.getKeyChar();
					if (!(code >= '0' && code <= '9')) {												//��1~9
						e.consume();																	//��������
					}
					if (!(jtf[0].getText().matches(match))) {											//����Χ����bug����1�ɴ���13
						jtf[0].setText("");																//��������
					}
					if (!(jtf[1].getText().matches(match))) {											//����Χ����bug����1�ɴ���13
						jtf[1].setText("");																//��������
					}
					if (!(jtf[2].getText().matches(match))) {											//����Χ����bug����1�ɴ���13
						jtf[2].setText("");																//��������
					}
					if (!(jtf[3].getText().matches(match))) {											//����Χ����bug����1�ɴ���13
						jtf[3].setText("");																//��������
					}
				}
			});
		}
	}
	private void New_JButton(JButton[] jb) {
		for (int i = 0; i < jb.length; i++) {
			jb[i] = new JButton(word[i]);
			jb[i].setFont(new Font("����",Font.BOLD,16));
			jb[i].setBounds(175*i+150,170,125,40);
		}
	}
	private void Bind_js_area(JScrollPane js,JTextArea jta){//�󶨻��������ı���
		jta.setEditable(false);
		js.setViewportView(jta);      																	//��
		jta.setLineWrap(true);																			//�ı��Զ�����
		js.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);						//ˮƽ������һֱ����ʾ
		js.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);						//��ֱ��������Ҫ����ʾ
		js.setBorder(BorderFactory.createLineBorder(Color.black));
		js.setBounds(150, 240, 300, 90);
	}
	private void Button_Actionlistener(){
		jb[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jtf[0].setText((1+r.nextInt(13))+"");
				jtf[1].setText((1+r.nextInt(13))+"");
				jtf[2].setText((1+r.nextInt(13))+"");
				jtf[3].setText((1+r.nextInt(13))+"");
			}
		});
		jb[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!(jtf[0].getText().matches(match)&&jtf[1].getText().matches(match)
						&&jtf[2].getText().matches(match)&&jtf[3].getText().matches(match))) {
					jta.setText("������������ԣ�");
				}
				else {
					String num[] = {
							jtf[0].getText(),
							jtf[1].getText(),
							jtf[2].getText(),
							jtf[3].getText(),
					};
					String character[] = {"+","-","*","/"};
					
					//������Ե�ʽ�Ӹ���
					int count = 0;
					String s = "";
					//����ѭ������ĸ����ֵ�λ�ã�4��=24��
					for (int i = 0; i < num.length; i++) {
						for (int j = 0; j < num.length; j++) {
							if (j != i) {
								for (int k = 0; k < num.length; k++) {
									if (k != i && k != j) {
										for (int l = 0; l < num.length; l++) {
											if (l != i && l != j && l != k) {
					//����ѭ������ĸ����ֵ�λ�ã�4^3=64��
												for (int x = 0; x < character.length; x++) {
													for (int y = 0; y < character.length; y++) {
														for (int z = 0; z < character.length; z++) {
					//ֱ������������ŷ�ʽ							
															//��1�����  ((a b) c) d
															if (Calculator(
																	"(("+num[i]+character[x]+num[j]+")"+character[y]+num[k]+")"+character[z]+num[l]
																			).equals("24")) {
																count++;
																s += "(("+num[i]+character[x]+num[j]+")"+character[y]+num[k]+")"+character[z]+num[l]+"\r\n";
															}
															//��2�����  (a b) (c d)
															if (Calculator(
																	"("+num[i]+character[x]+num[j]+")"+character[y]+"("+num[k]+character[z]+num[l]+")"
																			).equals("24")) {
																count++;
																s += "("+num[i]+character[x]+num[j]+")"+character[y]+"("+num[k]+character[z]+num[l]+")"+"\r\n";
															}
															//��3�����  (a (b c)) d
															if (Calculator(
																	"("+num[i]+character[x]+"("+num[j]+character[y]+num[k]+"))"+character[z]+num[l]
																			).equals("24")) {
																count++;
																s += "("+num[i]+character[x]+"("+num[j]+character[y]+num[k]+"))"+character[z]+num[l]+"\r\n";
															}
															//��4�����  a ((b c) d)
															if (Calculator(
																	num[i]+character[x]+"(("+num[j]+character[y]+num[k]+")"+character[z]+num[l]+")"
																			).equals("24")) {
																count++;
																s += num[i]+character[x]+"(("+num[j]+character[y]+num[k]+")"+character[z]+num[l]+")"+"\r\n";
															}
															//��5�����  a (b (c d))
															if (Calculator(
																	num[i]+character[x]+"("+num[j]+character[y]+"("+num[k]+character[z]+num[l]+"))"
																			).equals("24")) {
																count++;
																s += num[i]+character[x]+"("+num[j]+character[y]+"("+num[k]+character[z]+num[l]+"))"+"\r\n";
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
					if (count == 0) {
						s = "�޽⣡";
					}
					else {}
					jta.setText(s);
				}
			}
		});
	}
	private String Calculator(String s) {
		try {
			s = new ScriptEngineManager().getEngineByName("JavaScript").eval(s).toString();
		} catch (ScriptException e1) {
			e1.printStackTrace();
		}
		return s;
	}
	private void Add(){
		add(jtf[0]);
		add(jtf[1]);
		add(jtf[2]);
		add(jtf[3]);
		add(jb[0]);
		add(jb[1]);
		add(js);
	}
}
public class ver_1 {
	public static void main(String[] args) {
		new point();
	}
}