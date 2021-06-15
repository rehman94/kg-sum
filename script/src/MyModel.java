import java.util.List;

public class MyModel {


    private String QuestionId;

    private Parse Parse;




    public String getQuestionId() {
        return QuestionId;
    }


    public void setQuestionId(String questionId) {
        this.QuestionId = questionId;
    }


    public Parse getParse() {
        return Parse;
    }


    public void setParse(Parse parse) {
        this.Parse = parse;
    }



    public static class Parse {


        private String TopicEntityName;

        private String TopicEntityMid;

        private List<String> InferentialChain = null;

        private List<Object> Constraints = null;

        private Object Time;

        private Object Order;

        private List<Answer> Answers = null;


        public String getTopicEntityName() {
            return TopicEntityName;
        }

        public void setTopicEntityName(String topicEntityName) {
            this.TopicEntityName = topicEntityName;
        }

        public String getTopicEntityMid() {
            return TopicEntityMid;
        }

        public void setTopicEntityMid(String topicEntityMid) {
            this.TopicEntityMid = topicEntityMid;
        }

        public List<String> getInferentialChain() {
            return InferentialChain;
        }

        public void setInferentialChain(List<String> inferentialChain) {
            this.InferentialChain = inferentialChain;
        }


        public List<Object> getConstraints() {
            return Constraints;
        }

        public void setConstraints(List<Object> constraints) {
            this.Constraints = constraints;
        }

        public Object getTime() {
            return Time;
        }

        public void setTime(Object time) {
            this.Time = time;
        }

        public Object getOrder() {
            return Order;
        }

        public void setOrder(Object order) {
            this.Order = order;
        }

        public List<Answer> getAnswers() {
            return Answers;
        }

        public void setAnswers(List<Answer> answers) {
            this.Answers = answers;
        }


    }
    public static class Answer {

        private String AnswerType;
        private String AnswerArgument;
        private String EntityName;


        public String getAnswerType() {
            return AnswerType;
        }

        public void setAnswerType(String answerType) {
            this.AnswerType = answerType;
        }

        public String getAnswerArgument() {
            return AnswerArgument;
        }

        public void setAnswerArgument(String answerArgument) {
            this.AnswerArgument = answerArgument;
        }

        public String getEntityName() {
            return EntityName;
        }

        public void setEntityName(String entityName) {
            this.EntityName = entityName;
        }



    }
}
