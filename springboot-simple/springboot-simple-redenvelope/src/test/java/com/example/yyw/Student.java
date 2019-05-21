package com.example.yyw;

/**
 * @author ywyw2424@foxmail.com
 * @date 2019/4/30 14:27
 * @describe
 */
public class Student {

    private String name;
    private Integer num1;
    private Integer num2;

    public Student(String name, Integer num1, Integer num2) {
        this.name = name;
        this.num1 = num1;
        this.num2 = num2;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", num1=" + num1 +
                ", num2=" + num2 +
                '}';
    }

    public String getName() {
        return this.name;
    }

    public Integer getNum1() {
        return this.num1;
    }

    public Integer getNum2() {
        return this.num2;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNum1(Integer num1) {
        this.num1 = num1;
    }

    public void setNum2(Integer num2) {
        this.num2 = num2;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Student)) return false;
        final Student other = (Student) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final Object this$num1 = this.getNum1();
        final Object other$num1 = other.getNum1();
        if (this$num1 == null ? other$num1 != null : !this$num1.equals(other$num1)) return false;
        final Object this$num2 = this.getNum2();
        final Object other$num2 = other.getNum2();
        if (this$num2 == null ? other$num2 != null : !this$num2.equals(other$num2)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Student;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $num1 = this.getNum1();
        result = result * PRIME + ($num1 == null ? 43 : $num1.hashCode());
        final Object $num2 = this.getNum2();
        result = result * PRIME + ($num2 == null ? 43 : $num2.hashCode());
        return result;
    }
}
