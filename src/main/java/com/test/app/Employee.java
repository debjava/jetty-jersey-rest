package com.test.app;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Employee")
public class Employee {

  String employeeName;

  @XmlElement
  public String getEmployeeName() {
    return employeeName;
  }

  public void setEmployeeName(String employeeName) {
    this.employeeName = employeeName;
  }

  public String toXML() {
    String xmlContent = null;
    try {
      // Create JAXB Context
      JAXBContext jaxbContext = JAXBContext.newInstance(Employee.class);

      // Create Marshaller
      Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

      // Required formatting??
      jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

      // Print XML String to Console
      StringWriter sw = new StringWriter();

      // Write XML to StringWriter
      jaxbMarshaller.marshal(this, sw);

      // Verify XML Content
      xmlContent = sw.toString();
      System.out.println(xmlContent);

    } catch (JAXBException e) {
      e.printStackTrace();
    }
    return xmlContent;
  }

  public static void main(String[] args) {
    Employee emp = new Employee();
    emp.setEmployeeName("Sambit");
    System.out.println(emp.toXML());
  }
}
