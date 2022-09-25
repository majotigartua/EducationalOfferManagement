package educationaloffermanagement.model.pojo;

public class User {

    private String username;
    private String name;
    private String paternalSurname;
    private String maternalSurname;
    private String institutionalEmailAddress;
    private String password;
    private int idRole;
    private String role;
    private int responseCode;

    public User() {
    }

    public User(String name, String paternalSurname, String maternalSurname, String institutionalEmailAddress, String role) {
        this.name = name;
        this.paternalSurname = paternalSurname;
        this.maternalSurname = maternalSurname;
        this.institutionalEmailAddress = institutionalEmailAddress;
        this.role = role;
    }

    public User(String username, String name, String paternalSurname, String maternalSurname, String institutionalEmailAddress, String password, int idRole) {
        this.username = username;
        this.name = name;
        this.paternalSurname = paternalSurname;
        this.maternalSurname = maternalSurname;
        this.institutionalEmailAddress = institutionalEmailAddress;
        this.password = password;
        this.idRole = idRole;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPaternalSurname() {
        return paternalSurname;
    }

    public void setPaternalSurname(String paternalSurname) {
        this.paternalSurname = paternalSurname;
    }

    public String getMaternalSurname() {
        return maternalSurname;
    }

    public void setMaternalSurname(String maternalSurname) {
        this.maternalSurname = maternalSurname;
    }

    public String getInstitutionalEmailAddress() {
        return institutionalEmailAddress;
    }

    public void setInstitutionalEmailAddress(String institutionalEmailAddress) {
        this.institutionalEmailAddress = institutionalEmailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIdRole() {
        return idRole;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    @Override
    public String toString() {
        return name + " " + paternalSurname + " " + maternalSurname;
    }

    @Override
    public boolean equals(Object object) {
        boolean isEqual = false;
        if (object == this) {
            isEqual = true;
        }
        if ((object != null) && (object instanceof User)) {
            User user = (User) object;
            isEqual = (((this.getUsername() == null) ? user.getUsername() == null : this.getUsername().equals(user.getUsername()))
                    && ((this.getName() == null) ? user.getName() == null : this.getName().equals(user.getName()))
                    && ((this.getPaternalSurname()) == null ? user.getPaternalSurname() == null : this.getPaternalSurname().equals(user.getPaternalSurname()))
                    && ((this.getMaternalSurname() == null) ?  user.getPaternalSurname() == null : this.getPaternalSurname().equals(user.getPaternalSurname()))
                    && ((this.getInstitutionalEmailAddress() == null) ? user.getInstitutionalEmailAddress() ==  null :
                    this.getInstitutionalEmailAddress().equals(user.getInstitutionalEmailAddress()))
                    && (this.getPassword() == null ? user.getPassword() == null : this.getPassword().equals(user.getPassword()))
                    && (this.getIdRole() == user.getIdRole())
                    && (this.getRole() == null ? user.getRole() == null : this.getRole().equals(user.getRole()))
                    && (this.getResponseCode() == user.getResponseCode()));
        }
        return isEqual;
    }

}