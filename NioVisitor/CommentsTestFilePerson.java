package course.java.nio2;


public class CommentsTestFilePerson {
    // 0. commment after line
    private Long id; // 1. commment after line
    private String firstName; // 2. commment after line
    private String lastName;// 3. commment after line
    private int age;// 4. commment after line//commment after line
    private String phone;// 5. commment after line//commment after line/commment after line

    /**
     * 6. java doc
     * 6.java doc doc doc
     * 6.test test
     */
    public CommentsTestFilePerson() { //7.comment
    }

    public CommentsTestFilePerson(String firstName, String lastName, int age, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;/**  8.test test test  */
        this.age = age;/**  9.test test test  *//** 10.test test test  */
        this.phone = phone;
    }

    String test = "//test///aaaaaaa/bbbbbbbbbbbbbb/sssssssssss;//////ssss";

    public CommentsTestFilePerson(Long id, String firstName, String lastName, int age, String phone) {//11. aaaaaaaaaaaaaaaa
        this(firstName, lastName, age, phone);
        this.id = id;
    }//12.edqwweadasd

    public Long getId() {/* 13. eeeeeeeeeeeeeeeeeeeeeeeeeeee*/
        /* 14. eeeeeeeeeeeeeeeeeeeeeeeeeeee*/
        return id;

    }

    public void setId(Long id) {/** 15. eeeeeeeeeeeeeeeeeeeeee */
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
        /**
         * 16 . oooooooooooooooooooooooooooo
         * test test
         */
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }/**
     * 17 . oooooooooooooooooooooooooooo
     * test test
     */

    public String getLastName() {/* 18. This is an example of  multi-line comment.
     * The program prints "Hello, World!" to the standard output.
     */
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;/*19. This is an example of  multi-line comment.
         * The program prints "Hello, World!" to the standard output.
         */
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String /** 20
     * The HelloWorld program implements an application that
     * simply displays "Hello World!" to the standard output.
     *
     * @author  Zara Ali
     * @version 1.0
     * @since   2014-03-31
     */ getPhone() {
        return phone;
    }

    public void setPhone(String phone /** 21
     * This method is used to add two integers. This is
     * a the simplest form of a class method, just to
     * show the usage of various javadoc Tags.
     * @param numA This is the first paramter to addNum method
     * @param numB  This is the second parameter to addNum method
     * @return int This returns sum of numA and numB.
     */) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof /** 22
         * This method is used to add two integers. This is
         * a the simplest form of a class method, just to
         * show the usage of various javadoc Tags.
         * @param numA This is the first paramter to addNum method
         * @param numB  This is the second parameter to addNum method
         * @return int This returns sum of numA and numB.
         */ CommentsTestFilePerson)) return false;

        CommentsTestFilePerson person = (CommentsTestFilePerson) o;

        return getId( /** 23
         * This is the main method which makes use of addNum method.
         * @param args Unused.
         * @return Nothing.
         * @exception IOException On input error.
         * @see IOException
         */) != null ? getId().equals(person.getId()) : person.getId() == null;
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Person{" /**24
         * This is the main method which makes use of addNum method.
         * @param args Unused.
         * @return Nothing.
         * @exception IOException On input error.
         * @see IOException
         */);
    // 25random    sb.append("id=").append(id);
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", age=").append(age);
        sb.append(", phone='").append(phone).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public String format() {
        return String.format("| %3.3s | %-15.15s | %-15.15s | %-3d | %-15.15s |",
                id, firstName, lastName, age, phone);
    }

    public String format(String format) {

        return String.format(format, id, firstName, lastName, age, phone);
    }
    /** 26
     * This is the main method which makes use of addNum method.
     * @param args Unused.
     * @return Nothing.
     * @exception IOException On input error.
     * @see IOException
     */
}


/** 27
 * This is the main method which makes use of addNum method.
 * @param args Unused.
 * @return Nothing.
 * @exception IOException On input error.
 * @see IOException
 */