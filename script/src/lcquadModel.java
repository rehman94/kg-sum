import java.util.List;

public class lcquadModel {

    private String hybrid;

    private List<Question> question = null;

    private Boolean onlydbo;

    private Query query;

    private List<Answer> answers = null;

    private Boolean aggregation;

    private Integer id;

    public String getHybrid() {
        return hybrid;
    }

    public void setHybrid(String hybrid) {
        this.hybrid = hybrid;
    }

    public List<Question> getQuestion() {
        return question;
    }

    public void setQuestion(List<Question> question) {
        this.question = question;
    }

    public Boolean getOnlydbo() {
        return onlydbo;
    }

    public void setOnlydbo(Boolean onlydbo) {
        this.onlydbo = onlydbo;
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

    public Boolean getAggregation() {
        return aggregation;
    }

    public void setAggregation(Boolean aggregation) {
        this.aggregation = aggregation;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public class Head {


        private List<String> vars = null;


        public List<String> getVars() {
            return vars;
        }


        public void setVars(List<String> vars) {
            this.vars = vars;
        }

        private List<Object> link = null;


        public List<Object> getLink() {
            return link;
        }


        public void setLink(List<Object> link) {
            this.link = link;
        }

    }

    public class Query {

        private String sparql;

        public String getSparql() {
            return sparql;
        }


        public void setSparql(String sparql) {
            this.sparql = sparql;
        }
    }

    public class Question {

        private String string;
        private String language;

        public String getString() {
            return string;
        }

        public void setString(String string) {
            this.string = string;
        }


        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }
    }

    public class Results {


        private List<Binding> bindings = null;

        public List<Binding> getBindings() {
            return bindings;
        }

        public void setBindings(List<Binding> bindings) {
            this.bindings = bindings;
        }


    }

    public class Uri {


        private String type;

        private String value;


        public String getType() {
            return type;
        }


        public void setType(String type) {
            this.type = type;
        }


        public String getValue() {
            return value;
        }


        public void setValue(String value) {
            this.value = value;
        }
    }

    public class Answer {

        private Head head;
        private Results results;
        private Boolean booleannn;

        public Head getHead() {
            return head;
        }


        public void setHead(Head head) {
            this.head = head;
        }

        public Results getResults() {
            return results;
        }


        public void setResults(Results results) {
            this.results = results;
        }

        public Boolean getBoolean() {
            return booleannn;
        }

        public void setBoolean(Boolean _boolean) {
            this.booleannn = _boolean;
        }
    }

    public class Binding {

        private Uri uri;
        private Callret0 callret0;

        public Callret0 getCallret0() {
            return callret0;
        }

        public Uri getUri() {
            return uri;
        }

        public void setCallret0(Callret0 uri) {
            this.callret0 = uri;
        }
        public void setUri(Uri uri) {
            this.uri = uri;
        }


    }

    public class Callret0 {


        private String datatype;

        private String type;

        private String value;


        public String getDatatype() {
            return datatype;
        }


        public void setDatatype(String datatype) {
            this.datatype = datatype;
        }

        public String getType() {
            return type;
        }


        public void setType(String type) {
            this.type = type;
        }


        public String getValue() {
            return value;
        }


        public void setValue(String value) {
            this.value = value;
        }


    }
}
