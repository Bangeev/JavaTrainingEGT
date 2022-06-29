package course.java.jaxb;

import course.java.enums.Gender;
import course.java.enums.Role;
import course.java.enums.Status;

import course.java.jaxb.user.User;
import course.java.jaxb.user.UserList;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;

import static javax.xml.XMLConstants.W3C_XML_SCHEMA_NS_URI;

/**
 * Бях събмитнал още в петък но само за един user но прочетох условието че трябва да е Лист users и днес го преправих :)
 * може да съм изпуснал да изтрия някой излишен файл .. Извинявам се.
 */

public class CreateMarshal {
    public static void main(String[] args) throws Exception {
        UserList userList = new UserList();

        var user = new User(
                1L,
                "John Doe",
                "georgi",
                "georgi123",
                Gender.MALE,
                Role.ADMIN,
                "https://google.bg/",
                "this is shotr description",
                Status.ACTIVE,
                LocalDateTime.now(),
                LocalDateTime.now()
        );
        var user2 = new User(
                2L,
                "TEST2",
                "georgi",
                "georgi123",
                Gender.MALE,
                Role.ADMIN,
                "https://google.bg/",
                "this is shotr description",
                Status.ACTIVE,
                LocalDateTime.now(),
                LocalDateTime.now()
        );
        userList.setUsers(List.of(user,user2));
        final File f = new File("userSS.xml");

        // Illustrate two methods to create JAXBContext for j2s binding.
        // (1) by root classes newInstance(Class ...)
        JAXBContext context2 = JAXBContext.newInstance(UserList.class);

        // (2) by package, requires jaxb.index file in package cardfile.
        //     newInstance(String packageNames)
//        JAXBContext context2 = JAXBContext.newInstance("course.java.jaxb.cardfile");



        // illustrate optional unmarshal validation.
        Marshaller m2 = context2.createMarshaller();
        m2.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        m2.marshal(
            userList,
            new FileOutputStream(f));

        Unmarshaller um = context2.createUnmarshaller();
        um.setSchema(getSchema("users.xsd"));


    }

    /** returns a JAXP 1.3 schema by parsing the specified resource. */
    static Schema getSchema(String schemaResourceName)
        throws SAXException {
        SchemaFactory sf = SchemaFactory.newInstance(W3C_XML_SCHEMA_NS_URI);

        try {
            URL schemaURL = CreateMarshal.class.getClassLoader().getResource(schemaResourceName);
            return sf.newSchema(schemaURL);
        } catch (SAXException se) {
            // this can only happen if there's a deployment error and the resource is missing.
            throw se;
        }
    }



}
