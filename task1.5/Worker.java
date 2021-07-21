package task5;

public class Worker {

    private String Name;
    private int Age;
    private String Email;
    private String Phone;
    private String Position;
    private Float Payday;


    public Worker(String Name, int Age, String Position, String Phone, String Email, Float Payday) {
        this.Name = Name;
        this.Age = Age;
        this.Email = Email;
        this.Phone = Phone;
        this.Position = Position;
        this.Payday = Payday;
    }

    public int getAge() {
        return this.Age;
    }

    public void print() {
        System.out.printf("%s, \t%d лет, \tдолжность: \t%s, \t%s, \t%s, \t %f \n",
                this.Name,
                this.Age,
                this.Position,
                this.Phone,
                this.Email,
                this.Payday
                );
    }

}
