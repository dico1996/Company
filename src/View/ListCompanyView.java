package View;

import model.Company;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Dico on 08.06.16.
 */
public class ListCompanyView extends JFrame {

    public List<JButton> buttonList = new ArrayList<JButton>();;
    public JPanel panel;
    public JButton buttonAdd;
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem addCompanyMenu, updateBtn;
    private Company company;
    private List<Company> listCompany = new ArrayList<Company>();

    public ListCompanyView() {
        createFrame();
    }

    private void createFrame() {
        setTitle("Компании");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocation(100, 100);
        setSize(500, 500);
        panel = new JPanel();
        menuBar = new JMenuBar();
        menu = new JMenu("Действия");
        addCompanyMenu = new JMenuItem("Добавить компанию");
        updateBtn = new JMenuItem("Обновить");
        add(panel);
        setJMenuBar(menuBar);
        menuBar.add(menu);
        menu.add(addCompanyMenu);
        menu.add(updateBtn);
        loadButton();
        addCompanyMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CompanyInfoView companyInfoView = new CompanyInfoView(listCompany);
            }
        });
        updateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.removeAll();
                buttonList.clear();
                listCompany.clear();
                loadButton();
            }
        });
        setVisible(true);
    }

    private void addButtonInFrame() {
        for (JButton jButton : buttonList) {
            panel.add(jButton);
        }
        panel.revalidate();
    }

    public void loadButton() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            Document doc =  documentBuilder.parse(new File("people.xml"));

            NodeList nodeList = doc.getElementsByTagName("value");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);
                company = new Company();
                company.setName(element.getElementsByTagName("nameCompany").item(0).getChildNodes().item(0).getNodeValue());
                company.setAddr(element.getElementsByTagName("addr").item(0).getChildNodes().item(0).getNodeValue());
                company.setPostAddr(element.getElementsByTagName("postAddr").item(0).getChildNodes().item(0).getNodeValue());
                company.setTelephone(element.getElementsByTagName("telephone").item(0).getChildNodes().item(0).getNodeValue());
                company.setFioLiader(element.getElementsByTagName("fioLiader").item(0).getChildNodes().item(0).getNodeValue());
                listCompany.add(company);
                buttonAdd = new JButton(company.getName());
                buttonList.add(buttonAdd);
            }
        } catch (ParserConfigurationException e1) {
            e1.printStackTrace();
        } catch (SAXException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        addButtonInFrame();
    }
}
