package find;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Florin on 1/9/2017.
 */
public class Frame extends JFrame{
    private final JTextField nameField;
    private final JTextField regionField;
    private final JButton jButton;
    private final JPanel jPanel;
    public JTextArea jTextArea;
    Frame() {
        super("Meh");
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        nameField = new JTextField(10);

        regionField = new JTextField(10);

        jButton = new JButton();
        jTextArea = new JTextArea(10,10);

        jButton.setText("Search!");
        this.getRootPane().setDefaultButton(jButton);

        jPanel = new JPanel();
        getContentPane().add(jPanel);
        jPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.LINE_END;
        jPanel.add(new JLabel("Summoner name: "), c);
        c.gridy++;
        jPanel.add(new JLabel("Region: "),c);

        c.gridx = 1;
        c.gridy = 0;
        c.anchor = GridBagConstraints.LINE_START;
        jPanel.add(nameField,c);

        c.gridy++;
        jPanel.add(regionField,c);

        c.gridy++;
        c.anchor = GridBagConstraints.CENTER;
        jPanel.add(jButton,c);

        c.gridx = 0;
        c.gridy++;
        c.gridwidth = 4;
        c.gridheight = 4;
        c.fill = GridBagConstraints.BOTH;
        jPanel.add(jTextArea,c);

        this.add(jPanel);
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jTextArea.setText("");
                Summoner summoner = new Summoner(nameField.getText(),regionField.getText());
                jTextArea.append(String.valueOf(summoner.getID()) + "\n");
                jTextArea.append(summoner.getSummonerName() + "\n");
                String match = "";
                if (!summoner.isInMatch()) {
                    match = " not ";
                }
                jTextArea.append(summoner.getSummonerName() + " is" + match + "in match \n");
                jTextArea.append(summoner.getLastMatchDetails());
            }
        });
    }

    public static void main(String[] args) {
        Frame frame = new Frame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(400,400);
        //frame.pack();
        frame.setVisible(true);
    }
}
