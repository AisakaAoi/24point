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
	private final String[] word = {"生成","求解"};                             							//两个按钮文本
	private final JTextField jtf[] = new JTextField[4];													//4个显示数字
	private final JTextArea jta = new JTextArea();                   	 								//1个求解
	private final JScrollPane js = new JScrollPane();                       							//滑动条
	private final JButton jb[] = new JButton[word.length];                  							//两个按钮
	private final String match = "[1-9]?|1[0-3]";														//jt匹配串
	private Random r = new Random();																	//1-13随机生成
	private final Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();     				//获取屏幕
	public point() {
		setTitle("24点");																				//屏幕标题
		setLayout(null);																				//绝对布局
		New_JTextField(jtf);																			//生成文本框对象
		New_JButton(jb);																				//生成按钮对象
		Bind_js_area(js,jta);																			//绑定滑动条与求解文本框
		Button_Actionlistener();																		//按钮监听器
		Add();																							//添加组件
		setBounds((int)(screensize.getWidth()/2-300),(int)(screensize.getHeight()/2-200),600,400);		//屏幕默认显示在显示器中央
		setResizable(false);																			//窗口大小不可改变
		getContentPane().setBackground(Color.white);													//白底
		setVisible(true);																				//可见
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);										//进程关闭
	}
	private void New_JTextField(final JTextField[] jtf) {
		for (int i = 0; i < jtf.length; i++) {															//循环生成文本框
			jtf[i] = new JTextField();																	//创建对象
			jtf[i].setHorizontalAlignment(JTextField.CENTER);											//文字居中
			jtf[i].setFont(new Font("黑体",Font.BOLD,55));												//设置字体
			jtf[i].setBounds(126*i+61, 40, 100, 100);													//设置文本框位置
			jtf[i].addKeyListener(new KeyAdapter() {													//键入监听器
				public void keyTyped(KeyEvent e) {
					char code = e.getKeyChar();
					if (!(code >= '0' && code <= '9')) {												//非1~9
						e.consume();																	//不可输入
					}
					if (!(jtf[0].getText().matches(match))) {											//超范围（有bug：先1可大于13
						jtf[0].setText("");																//不可输入
					}
					if (!(jtf[1].getText().matches(match))) {											//超范围（有bug：先1可大于13
						jtf[1].setText("");																//不可输入
					}
					if (!(jtf[2].getText().matches(match))) {											//超范围（有bug：先1可大于13
						jtf[2].setText("");																//不可输入
					}
					if (!(jtf[3].getText().matches(match))) {											//超范围（有bug：先1可大于13
						jtf[3].setText("");																//不可输入
					}
				}
			});
		}
	}
	private void New_JButton(JButton[] jb) {
		for (int i = 0; i < jb.length; i++) {
			jb[i] = new JButton(word[i]);
			jb[i].setFont(new Font("黑体",Font.BOLD,16));
			jb[i].setBounds(175*i+150,170,125,40);
		}
	}
	private void Bind_js_area(JScrollPane js,JTextArea jta){//绑定滑动条与文本框
		jta.setEditable(false);
		js.setViewportView(jta);      																	//绑定
		jta.setLineWrap(true);																			//文本自动换行
		js.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);						//水平滑动条一直不显示
		js.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);						//竖直滑动条需要就显示
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
					jta.setText("输入错误请重试！");
				}
				else {
					String num[] = {
							jtf[0].getText(),
							jtf[1].getText(),
							jtf[2].getText(),
							jtf[3].getText(),
					};
					String character[] = {"+","-","*","/"};
					
					//计算可以的式子个数
					int count = 0;
					String s = "";
					//四重循环穷举四个数字的位置：4！=24种
					for (int i = 0; i < num.length; i++) {
						for (int j = 0; j < num.length; j++) {
							if (j != i) {
								for (int k = 0; k < num.length; k++) {
									if (k != i && k != j) {
										for (int l = 0; l < num.length; l++) {
											if (l != i && l != j && l != k) {
					//三重循环穷举四个数字的位置：4^3=64种
												for (int x = 0; x < character.length; x++) {
													for (int y = 0; y < character.length; y++) {
														for (int z = 0; z < character.length; z++) {
					//直接穷举五种括号方式							
															//第1种情况  ((a b) c) d
															if (Calculator(
																	"(("+num[i]+character[x]+num[j]+")"+character[y]+num[k]+")"+character[z]+num[l]
																			).equals("24")) {
																count++;
																s += "(("+num[i]+character[x]+num[j]+")"+character[y]+num[k]+")"+character[z]+num[l]+"\r\n";
															}
															//第2种情况  (a b) (c d)
															if (Calculator(
																	"("+num[i]+character[x]+num[j]+")"+character[y]+"("+num[k]+character[z]+num[l]+")"
																			).equals("24")) {
																count++;
																s += "("+num[i]+character[x]+num[j]+")"+character[y]+"("+num[k]+character[z]+num[l]+")"+"\r\n";
															}
															//第3种情况  (a (b c)) d
															if (Calculator(
																	"("+num[i]+character[x]+"("+num[j]+character[y]+num[k]+"))"+character[z]+num[l]
																			).equals("24")) {
																count++;
																s += "("+num[i]+character[x]+"("+num[j]+character[y]+num[k]+"))"+character[z]+num[l]+"\r\n";
															}
															//第4种情况  a ((b c) d)
															if (Calculator(
																	num[i]+character[x]+"(("+num[j]+character[y]+num[k]+")"+character[z]+num[l]+")"
																			).equals("24")) {
																count++;
																s += num[i]+character[x]+"(("+num[j]+character[y]+num[k]+")"+character[z]+num[l]+")"+"\r\n";
															}
															//第5种情况  a (b (c d))
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
						s = "无解！";
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