package chen.questiong.database;

/**
 * 保存数据库数据
 * Created by chen on 2016/11/4.
 */
public class Question {

    /**
     * 对应的就是Filter1-7  还有一个选中答案
     */

    //编号
    public int ID;
    //问题
    public String question;
    //四个选项
    public String answerA;
    public String answerB;
    public String answerC;
    //详情
    public String answerD;
    //答案
    public int answer;
    public String explaination;

    //用户选中的答案
    public int selectedAnswer;

}
