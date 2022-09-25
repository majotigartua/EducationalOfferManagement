package educationaloffermanagement.model.pojo;

public class EducationalExperience {

    private int IdEducationalExperience;
    private String name;

    public EducationalExperience() {
    }

    public EducationalExperience(int IdEducationalExperience, String name) {
        this.IdEducationalExperience = IdEducationalExperience;
        this.name = name;
    }

    public EducationalExperience(String name) {
        this.name = name;
    }

    public int getIdEducationalExperience() {
        return IdEducationalExperience;
    }

    public void setIdEducationalExperience(int IdEducationalExperience) {
        this.IdEducationalExperience = IdEducationalExperience;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object object) {
        boolean isEqual = false;
        if (object == this) {
            isEqual = true;
        }
        if ((object != null) && (object instanceof EducationalExperience)) {
            EducationalExperience educationalExperience = (EducationalExperience) object;
            isEqual = ((this.getIdEducationalExperience() == educationalExperience.getIdEducationalExperience()) &&
                    (this.getName().equals(educationalExperience.getName())));
        }
        return isEqual;
    }

}