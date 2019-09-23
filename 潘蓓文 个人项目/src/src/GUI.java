package src;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GUI {
    private JFrame mainframe;
    private JPanel contentPanel;
    String orders[] = {null};
    File file;

    public GUI () {
        mainframe = new JFrame("WC");
        mainframe.setBounds(100,100,720,480);
        mainframe.setVisible(true);

        contentPanel = new JPanel();
        contentPanel.setBorder(new EmptyBorder(5,5,5,5));
        mainframe.setContentPane(contentPanel);
        contentPanel.setLayout(null);
        contentPanel.setVisible(true);

        JTextArea jta1 = new JTextArea();
        jta1.setLineWrap(true);
        jta1.setBounds(20,40,400,360);
        jta1.setEditable(false);
        jta1.setVisible(true);
        contentPanel.add(jta1);

        JTextArea jta2 = new JTextArea();
        jta2.setLineWrap(true);
        jta2.setBounds(450,40,235,130);
        jta2.setEditable(false);
        jta2.setVisible(true);
        contentPanel.add(jta2);

        JButton button1 = new JButton("打开文件");
        button1.setBounds(460,210,95,30);
        button1.setVisible(true);
        contentPanel.add(button1);

        JButton button2 = new JButton("统计字符数");
        button2.setBounds(580,210,95,30);
        button2.setVisible(true);
        contentPanel.add(button2);

        JButton button3 = new JButton("统计单词数");
        button3.setBounds(460,260,95,30);
        button3.setVisible(true);
        contentPanel.add(button3);

        JButton button4 = new JButton("统计行数");
        button4.setBounds(580,260,95,30);
        button4.setVisible(true);
        contentPanel.add(button4);

        JButton button5 = new JButton("统计代码行数、空行数、注释行数");
        button5.setBounds(460,310,215,30);
        button5.setVisible(true);
        contentPanel.add(button5);

        mainframe.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                FileDialog dialog = new FileDialog(mainframe,"打开文件",FileDialog.LOAD);
                dialog.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent windowEvent) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);

                file = new File(dialog.getDirectory() + dialog.getFile());
                jta2.append("你所指定的文件名为"+dialog.getDirectory()+dialog.getFile()+"\n");
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    BufferedReader br=new BufferedReader(new FileReader(file));

                    int charcount = 0;
                    String c;
                    while((c=br.readLine())!=null) {
                        charcount+=c.length();
                    }

                    jta1.append("字符数："+charcount+'\n');
                    br.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    BufferedReader br = new BufferedReader(new FileReader(file));

                    int wordcount = 0;
                    String w = null;
                    String s = "[A-Za-z]+\\b";
                    Pattern p = Pattern.compile(s);
                    while ((w = br.readLine()) != null) {
                        Matcher m = p.matcher(w);
                        while (m.find()) {
                            wordcount++;
                        }
                    }

                    jta1.append("单词数：" + wordcount+'\n');
                    br.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    BufferedReader br=new BufferedReader(new FileReader(file));

                    int linecount=0;
                    while((br.readLine())!=null) {
                        linecount++;
                    }

                    jta1.append("行数："+linecount+'\n');
                    br.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    BufferedReader br = new BufferedReader(new FileReader(file));

                    int codelinecount = 0;
                    int blanklinecount = 0;
                    int notelinecount = 0;

                    String r = "\\s*";
                    String sl;
                    boolean flag = false;
                    while ((sl = br.readLine()) != null) {
                        sl = sl.trim();

                        if (sl.contains("/*")) {
                            notelinecount++;
                            flag = true;
                            if (!(sl.startsWith("/*"))) {
                                codelinecount++;
                            }
                        } else if (flag == true) {
                            notelinecount++;
                            if (sl.contains("*/")) {
                                flag = false;
                                if (!(sl.startsWith("*/"))) {
                                    codelinecount++;
                                }
                            }
                        } else if (sl.contains("//")) {
                            notelinecount++;
                            if (!(sl.startsWith("//"))) {
                                codelinecount++;
                            }
                        } else if (sl.matches(r)) {
                            blanklinecount++;
                        } else codelinecount++;
                    }

                    jta1.append("代码行数，空行数，注释行数：" + codelinecount + " " + blanklinecount + " " + notelinecount+'\n');
                    br.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args) {
        GUI test = new GUI();
    }
}
