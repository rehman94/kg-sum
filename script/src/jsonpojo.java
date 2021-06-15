import java.util.List;

public class jsonpojo {
    public String id;

    public String answertype;

    public Boolean aggregation;

    public Boolean onlydbo;

    public Boolean hybrid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAnswertype() {
        return answertype;
    }

    public void setAnswertype(String answertype) {
        this.answertype = answertype;
    }

    public Boolean getAggregation() {
        return aggregation;
    }

    public void setAggregation(Boolean aggregation) {
        this.aggregation = aggregation;
    }

    public Boolean getOnlydbo() {
        return onlydbo;
    }

    public void setOnlydbo(Boolean onlydbo) {
        this.onlydbo = onlydbo;
    }

    public Boolean getHybrid() {
        return hybrid;
    }

    public void setHybrid(Boolean hybrid) {
        this.hybrid = hybrid;
    }

    public List<Question> getQuestion() {
        return question;
    }

    public void setQuestion(List<Question> question) {
        this.question = question;
    }

    public Query getQuery() {
        return query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public List<Question> question = null;

    public Query query;

    public List<Answer> answers = null;

    public class Query {

        public String sparql;

        public String getSparql() {
            return sparql;
        }

        public void setSparql(String sparql) {
            this.sparql = sparql;
        }
    }
    public class Question {


        public String language;

        public String string;

        public String keywords;

    }

    public class Results {

        public List<Binding> bindings = null;

    }
    public class Uri {


        public String type;

        public String value;

    }
    public class list {


        public String type;

        public String value;

    }
    public class Binding {


        public Uri uri;

        public C c;

        public Date date;
        public Strng strng;
        public list list;

    }
    public class Answer {


        public Head head;

        public Results results;

        public Boolean aBoolean;



    }
    public class C {


        public String type;

        public String value;

    }
    public class Date {


        public String type;

        public String value;


    }
    public class Strng {


        public String type;

        public String value;


    }
    public class Head {


        public List<String> vars = null;

    }
}