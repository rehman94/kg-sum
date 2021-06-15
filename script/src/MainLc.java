import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainLc {
    private static FileWriter file;
    private static FileWriter fileMid;
    public static String[] stopwords = {"?","!","a", "as", "able", "about", "above", "according", "accordingly", "across", "actually", "after", "afterwards", "again", "against", "aint", "all", "allow", "allows", "almost", "alone", "along", "already", "also", "although", "always", "am", "among", "amongst", "an", "and", "another", "any", "anybody", "anyhow", "anyone", "anything", "anyway", "anyways", "anywhere", "apart", "appear", "appreciate", "appropriate", "are", "arent", "around", "as", "aside", "ask", "asking", "associated", "at", "available", "away", "awfully", "be", "became", "because", "become", "becomes", "becoming", "been", "before", "beforehand", "behind", "being", "believe", "below", "beside", "besides", "best", "better", "between", "beyond", "both", "brief", "but", "by", "cmon", "cs", "came", "can", "cant", "cannot", "cant", "cause", "causes", "certain", "certainly", "changes", "clearly", "co", "com", "come", "comes", "concerning", "consequently", "consider", "considering", "contain", "containing", "contains", "corresponding", "could", "couldnt", "course", "currently", "definitely", "described", "despite", "did", "didnt", "different", "do", "does", "doesnt", "doing", "dont", "done", "down", "downwards", "during", "each", "edu", "eg", "eight", "either", "else", "elsewhere", "enough", "entirely", "especially", "et", "etc", "even", "ever", "every", "everybody", "everyone", "everything", "everywhere", "ex", "exactly", "example", "except", "far", "few", "ff", "fifth", "first", "five", "followed", "following", "follows", "for", "former", "formerly", "forth", "four", "from", "further", "furthermore", "get", "gets", "getting", "given", "gives", "go", "goes", "going", "gone", "got", "gotten", "greetings", "had", "hadnt", "happens", "hardly", "has", "hasnt", "have", "havent", "having", "he", "hes", "hello", "help", "hence", "her", "here", "heres", "hereafter", "hereby", "herein", "hereupon", "hers", "herself", "hi", "him", "himself", "his", "hither", "hopefully", "how", "howbeit", "however", "i", "id", "ill", "im", "ive", "ie", "if", "ignored", "immediate", "in", "inasmuch", "inc", "indeed", "indicate", "indicated", "indicates", "inner", "insofar", "instead", "into", "inward", "is", "isnt", "it", "itd", "itll", "its", "its", "itself", "just", "keep", "keeps", "kept", "know", "knows", "known", "last", "lately", "later", "latter", "latterly", "least", "less", "lest", "let", "lets", "like", "liked", "likely", "little", "look", "looking", "looks", "ltd", "mainly", "many", "may", "maybe", "me", "mean", "meanwhile", "merely", "might", "more", "moreover", "most", "mostly", "much", "must", "my", "myself", "name", "namely", "nd", "near", "nearly", "necessary", "need", "needs", "neither", "never", "nevertheless", "new", "next", "nine", "no", "nobody", "non", "none", "noone", "nor", "normally", "not", "nothing", "novel", "now", "nowhere", "obviously", "of", "off", "often", "oh", "ok", "okay", "old", "on", "once", "one", "ones", "only", "onto", "or", "other", "others", "otherwise", "ought", "our", "ours", "ourselves", "out", "outside", "over", "overall", "own", "particular", "particularly", "per", "perhaps", "placed", "please", "plus", "possible", "presumably", "probably", "provides", "que", "quite", "qv", "rather", "rd", "re", "really", "reasonably", "regarding", "regardless", "regards", "relatively", "respectively", "right", "said", "same", "saw", "say", "saying", "says", "second", "secondly", "see", "seeing", "seem", "seemed", "seeming", "seems", "seen", "self", "selves", "sensible", "sent", "serious", "seriously", "seven", "several", "shall", "she", "should", "shouldnt", "since", "six", "so", "some", "somebody", "somehow", "someone", "something", "sometime", "sometimes", "somewhat", "somewhere", "soon", "sorry", "specified", "specify", "specifying", "still", "sub", "such", "sup", "sure", "ts", "take", "taken", "tell", "tends", "th", "than", "thank", "thanks", "thanx", "that", "thats", "thats", "the", "their", "theirs", "them", "themselves", "then", "thence", "there", "theres", "thereafter", "thereby", "therefore", "therein", "theres", "thereupon", "these", "they", "theyd", "theyll", "theyre", "theyve", "think", "third", "this", "thorough", "thoroughly", "those", "though", "three", "through", "throughout", "thru", "thus", "to", "together", "too", "took", "toward", "towards", "tried", "tries", "truly", "try", "trying", "twice", "two", "un", "under", "unfortunately", "unless", "unlikely", "until", "unto", "up", "upon", "us", "use", "used", "useful", "uses", "using", "usually", "value", "various", "very", "via", "viz", "vs", "want", "wants", "was", "wasnt", "way", "we", "wed", "well", "were", "weve", "welcome", "well", "went", "were", "werent", "what", "whats", "whatever", "when", "whence", "whenever", "where", "wheres", "whereafter", "whereas", "whereby", "wherein", "whereupon", "wherever", "whether", "which", "while", "whither", "who", "whos", "whoever", "whole", "whom", "whose", "why", "will", "willing", "wish", "with", "within", "without", "wont", "wonder", "would", "would", "wouldnt", "yes", "yet", "you", "youd", "youll", "youre", "youve", "your", "yours", "yourself", "yourselves", "zero"};
    public static ArrayList<String> wordsList;

    public static void main (String[] arg) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        Gson gson = new Gson();
        JSONArray jsonArray = (JSONArray) parser.parse(new FileReader("F://script/lcquad.json"));

        Type listType = new TypeToken<List<lcquadModel>>() {
        }.getType();

        List<lcquadModel> list = gson.fromJson(String.valueOf(jsonArray), listType);

        //SET ID
        String id = "q" + list.get(0).getId().toString();
        //SET IC
        List<String> IC = getIC(list.get(0).getQuestion().get(0).getString());
        //SET TOPIC ENTITY NAME AND MID
        String TEN = getEntityName(list.get(1).getQuery().getSparql(), "name");
        String TED = getEntityName(list.get(1).getQuery().getSparql(), "id");

        //NEW JSON FILE;
        List<MyModel> myNewJSON = new ArrayList<MyModel>();
        MyModel mymodelObj;
        MyModel.Parse parseObj;
        List<MyModel.Answer> answerObjList;
        MyModel.Answer answerObj;
        List<Object> empty = new ArrayList<Object>();
        for (lcquadModel myListItem: list) {
            mymodelObj = new MyModel();
            parseObj = new MyModel.Parse();
            answerObj = new MyModel.Answer();
            answerObjList = new ArrayList<MyModel.Answer>();
            mymodelObj.setQuestionId("q" + myListItem.getId().toString());
            parseObj.setConstraints(empty);
            parseObj.setTopicEntityMid(getEntityName(myListItem.getQuery().getSparql(),"id"));
            parseObj.setTopicEntityName(getEntityName(myListItem.getQuery().getSparql(),"name"));
            parseObj.setInferentialChain(getIC(myListItem.getQuestion().get(0).getString()));
            String parsed = null;
            for (lcquadModel.Answer u : myListItem.getAnswers()) {

                if (u.getResults() != null) {
                    if (u.getResults().getBindings() != null) {
                        for (lcquadModel.Binding b : u.getResults().getBindings()) {
                            MyModel.Answer temp = new MyModel.Answer();
                            if(b.getUri() != null) {
                                if (b.getUri().getType().equals("uri")) {
                                    temp.setAnswerType("Entity");
                                    temp.setAnswerArgument("<" + b.getUri().getValue() + ">");
                                    parsed = StringUtils.substringAfter(b.getUri().getValue(), "http://dbpedia.org/resource/")
                                            .replaceAll("\\(.*\\)", "");
                                    parsed = parsed.replaceAll("[_]", " ");
                                    temp.setEntityName(parsed);
                                    answerObjList.add(temp);
                                }
                            }
                            else if (b.getCallret0() != null)
                            {
                                temp.setAnswerType("Numeric");
                                temp.setAnswerArgument(b.getCallret0().getValue());
                                parsed ="";
                                temp.setEntityName(parsed);
                                answerObjList.add(temp);
                            }
                        }
                    }
                }
                else if(u.getBoolean() !=null){

                    MyModel.Answer temp = new MyModel.Answer();
                    temp.setAnswerType("boolean");
                    temp.setAnswerArgument( u.getBoolean().toString());
                    parsed = "";
                    temp.setEntityName(parsed);
                    answerObjList.add(temp);
                }
                else{
                    MyModel.Answer temp = new MyModel.Answer();
                    temp.setAnswerType("");
                    temp.setEntityName("");
                    temp.setAnswerArgument("");
                    answerObjList.add(temp);
                }

            }

            parseObj.setAnswers(answerObjList);


            mymodelObj.setParse(parseObj);

            myNewJSON.add(mymodelObj);
    }



        try {
            Gson gson1 = new GsonBuilder().create();
            JsonArray myCustomArray = gson1.toJsonTree(myNewJSON).getAsJsonArray();
            for (int i = 0; i < myCustomArray.size(); i++){
                MyModel m =gson1.fromJson(myCustomArray.get(i), MyModel.class);
                System.out.println("-----");
                file = new FileWriter("F:/script/lcquery/" + m.getQuestionId() + ".json");
                fileMid = new FileWriter("F:/script/lc-mid/" + m.getQuestionId() + ".list");
                file.write(myCustomArray.get(i).toString());
                fileMid.write(m.getQuestionId());
                System.out.println(i);
                file.flush();
                fileMid.flush();

            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally {

            try {
                fileMid.flush();
                file.flush();
                fileMid.close();
                file.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }




    }

    public static List<String> getIC(String question){
        wordsList = new ArrayList<String>();
        String str = question.toLowerCase();
        str = str.trim().replaceAll("\\s+", " ");
        str = str.replaceAll("[^a-zA-Z0-9]", " ");
        String[] words = str.split(" ");


        for (String word : words) {
            wordsList.add(word);
        }

        //remove stop words here from the temp list
        for (int j = 0; j < stopwords.length; j++) {
            if (wordsList.contains(stopwords[j])) {
                wordsList.remove(stopwords[j]);//remove it
            }
        }


        return wordsList;

    }

    public static String getEntityName(String string, String nameOrId) {
        String TEMID = "";
        String TENAME = "";

            String[] sparqlArray = string.split(" ");

        for (String item : sparqlArray) {
            if (item.contains("<http://dbpedia.org/resource/")) {
                TEMID = item;
                TENAME = item.split("<http://dbpedia.org/resource/")[1];
                TENAME = TENAME.replaceAll("[^a-zA-Z0-9]", " ");
            }
        }
        //System.out.println(TEMID );
        if(nameOrId =="name")
        {

            return TENAME;
        }
        else {

            return TEMID;
        }
    }

}
