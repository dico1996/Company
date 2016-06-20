package View;

import model.Company;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dico on 17.06.16.
 */
public class CompanyInfoView extends JDialog {
    private JPanel panel;
    private JButton buttonAddInfo;
    private JLabel name, addr, telephone, postAddr, fioLiader;
    private JTextField tfName, tfAddr, tfTelephone, tfPostAddr, tfFioLiader;
    private Company company;
    private List<Company> listCompany;

    public CompanyInfoView(List<Company> listCompany) {
        this.listCompany = listCompany;
        setTitle("Информация о компании");
        setLocation(100, 100);
        panel = new JPanel();
        buttonAddInfo = new JButton("Сохранить");
        buttonAddInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveCompany();
            }
        });
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        add(panel);
        panel.add(buttonAddInfo);
        addLable();
        setVisible(true);
        pack();
    }

    private void addLable() {
        name = new JLabel("Название");
        addr = new JLabel("Адрес");
        telephone = new JLabel("Телефон");
        postAddr = new JLabel("Почтовый адрес");
        fioLiader = new JLabel("ФИО руководителя");

        tfName = new JTextField(10);
        tfAddr = new JTextField(10);
        tfTelephone = new JTextField(10);
        tfPostAddr = new JTextField(10);
        tfFioLiader = new JTextField(10);

        panel.add(name);
        panel.add(tfName);
        panel.add(addr);
        panel.add(tfAddr);
        panel.add(telephone);
        panel.add(tfTelephone);
        panel.add(postAddr);
        panel.add(tfPostAddr);
        panel.add(fioLiader);
        panel.add(tfFioLiader);
    }

    public void saveCompany() {
        company = new Company();
        company.setName(tfName.getText());
        company.setAddr(tfAddr.getText());
        company.setPostAddr(tfPostAddr.getText());
        company.setTelephone(tfTelephone.getText());
        company.setFioLiader(tfFioLiader.getText());
        listCompany.add(company);

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            Element dataEl = document.createElement("data");
            document.appendChild(dataEl);

            for (int i = 0; i < listCompany.size(); i++) {
                Element valueEl = document.createElement("value");
                Attr attrDepartment = document.createAttribute("id");
                attrDepartment.setValue(String.valueOf(i));
                valueEl.setAttributeNode(attrDepartment);
                dataEl.appendChild(valueEl);

                Element nameCompany = document.createElement("nameCompany");
                nameCompany.appendChild(document.createTextNode(listCompany.get(i).getName()));
                valueEl.appendChild(nameCompany);

                Element addr = document.createElement("addr");
                addr.appendChild(document.createTextNode(listCompany.get(i).getAddr()));
                valueEl.appendChild(addr);

                Element postAddr = document.createElement("postAddr");
                postAddr.appendChild(document.createTextNode(listCompany.get(i).getPostAddr()));
                valueEl.appendChild(postAddr);

                Element telephone = document.createElement("telephone");
                telephone.appendChild(document.createTextNode(listCompany.get(i).getTelephone()));
                valueEl.appendChild(telephone);

                Element fioLiader = document.createElement("fioLiader");
                fioLiader.appendChild(document.createTextNode(listCompany.get(i).getFioLiader()));
                valueEl.appendChild(fioLiader);
            }

            TransformerFactory factoryTr = TransformerFactory.newInstance();
            Transformer transformer = factoryTr.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamFile = new StreamResult(new File("people.xml"));

            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(domSource,streamFile);

        } catch (ParserConfigurationException e1) {
            e1.printStackTrace();
        } catch (TransformerConfigurationException e1) {
            e1.printStackTrace();
        } catch (TransformerException e1) {
            e1.printStackTrace();
        }
    }
}
