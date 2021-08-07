package UI.models.timeTable;

import UI.ValidationException;
import UI.models.Lesson;
import engine.models.Solution;
import schema.models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class TimeTableMembers
{
    private int days;
    private int hours;
    private List<Teacher> teachers;
    private List<Subject> subjects;
    private List<Grade> grades;
    private List<Rule> rules;
    private int hardRulesWeight;

    public TimeTableMembers(ETTTimeTable timeTableMembers) throws ValidationException {
        setDays(timeTableMembers.getDays());
        setHours(timeTableMembers.getHours());
        setTeachers(timeTableMembers.getETTTeachers().getETTTeacher());
        setGrades(timeTableMembers.getETTClasses().getETTClass());
        setSubjects(timeTableMembers.getETTSubjects().getETTSubject());
        setRules(timeTableMembers.getETTRules().getETTRule());
        setHardRulesWeight(timeTableMembers.getETTRules().getHardRulesWeight());
    }

    public int getHardRulesWeight() {
        return hardRulesWeight;
    }

    public void setHardRulesWeight(int hardRulesWeight) {
        this.hardRulesWeight = hardRulesWeight;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<ETTTeacher> ettTeachers) throws ValidationException {
        this.teachers = new ArrayList<Teacher>();
        for (ETTTeacher ettTeacher : ettTeachers) {
            try {
                Teacher teacher = new Teacher(ettTeacher);
                this.teachers.add(teacher);
            } catch (ValidationException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    //TODO : add validation on "Subject" class constructor (in the set function) - Example in "Teacher" class
    public void setSubjects(List<ETTSubject> ettSubjects) {
        this.subjects = new ArrayList<Subject>();
        for (ETTSubject ettTeacher : ettSubjects) {
            //try {
                Subject subject = new Subject(ettSubjects);
                this.subjects.add(subject);
            //}
            //catch (ValidationException e) {
            //    e.printStackTrace();
            //}
        }    }

    public List<Grade> getGrades() {
        return grades;
    }

    //TODO : add validation on "Grade" class constructor (in the set function) - Example in "Teacher" class
    public void setGrades(List<ETTClass> ettClasses) throws ValidationException {
        this.grades = new ArrayList<Grade>();
        for (ETTClass ettClass : ettClasses) {
            //try {
            Grade grade = new Grade(ettClass);
            this.grades.add(grade);
            //}
            //catch (ValidationException e) {
            //    e.printStackTrace();
            //}
        }
    }

    public List<Rule> getRules() {
        return rules;
    }

    //TODO : add validation on "Rules" class constructor (in the set function) - Example in "Teacher" class
        private void setRules(List<ETTRule> ettRules) throws ValidationException {
            this.rules = new ArrayList<Rule>();
            for (ETTRule ettRule : ettRules) {
                //try {
                Rule rule = new Rule(ettRule);
                this.rules.add(rule);
                //}
                //catch (ValidationException e) {
                //    e.printStackTrace();
                //}
            }
        }

    public Solution<Lesson> generateRandomSolution() {

        Solution<Lesson> solution = new Solution<>();

        List<Integer> teachersIds = this.teachers.stream().map(Teacher::getId).collect(Collectors.toList());
        List<Integer> subjectsIds = this.subjects.stream().map(Subject::getId).collect(Collectors.toList());
        subjectsIds.add(-1); //add option of empty lesson

        Random rand = new Random();
        grades.forEach(grade -> {
            for (int d = 1; d <= days; d++) {
                for (int h = 1; h <= hours; h++) {
                    int randomSubject = subjectsIds.get(rand.nextInt(subjectsIds.size()));
                    int randomTeacher = randomSubject == -1 ? -1 : teachersIds.get(rand.nextInt(teachersIds.size()));

                    Lesson lesson = new Lesson(grade.getId(), randomTeacher, randomSubject, d, h);
                    solution.addItemToList(lesson);
                }
            }
        });
        return solution;
    }
}
