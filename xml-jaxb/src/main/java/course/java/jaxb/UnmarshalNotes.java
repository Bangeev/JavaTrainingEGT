/**
 * $Id: UsingJAXBTest1.java,v 1.1 2003/01/01 03:18:32 bhakti Exp $
 * Copyright (c) 2003 Sun Microsystems, Inc. All Rights Reserved.
 * <p>
 * This software is the confidential and proprietary information of Sun
 * Microsystems, Inc. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Sun.
 * <p>
 * SUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
 * SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE, OR NON-INFRINGEMENT. SUN SHALL NOT BE LIABLE FOR ANY DAMAGES
 * SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING
 * THIS SOFTWARE OR ITS DERIVATIVES.
 */

package course.java.jaxb;


import course.java.jaxb.model.UserList;

import java.io.File;
import java.util.List;

import javax.xml.bind.*;

//import notes.jaxb.Note;
//import notes.jaxb.Notes;


/**
 * This shows how to use JAXB to unmarshal an xml file
 * Then display the information from the content tree
 */

public class UnmarshalNotes {


    public static void main(String args[]) {
        try {
            JAXBContext jc = JAXBContext.newInstance("course.java.jaxb.model");
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            //unmarshaller.setValidating(true);

            UserList users = (UserList) JAXBIntrospector.getValue(unmarshaller.unmarshal(new File("userSS.xml")));
//
            List<UserList.User> userLists = users.getUser();

            for (int i = 0; i < userLists.size(); i++) {
                System.out.println("Note  details ");
                UserList.User user = (UserList.User) userLists.get(i);

                System.out.println("name: " + user.getName());
                System.out.println("username: " + user.getUsername());
                System.out.println("password: " + user.getPassword());
                System.out.println("gender: " + user.getGender());
                System.out.println("role: " + user.getRole());
                System.out.println("profilePhoto: " + user.getProfilePhoto());
                System.out.println("description: " + user.getDescription());
                System.out.println("status: " + user.getStatus());
                System.out.println("created: " + user.getCreated());
                System.out.println("modified: " + user.getModified());
                System.out.println();
            }


        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }

    }
}