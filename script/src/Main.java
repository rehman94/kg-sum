import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

public class Main {
    private static FileWriter file;
    private static FileWriter fileMid;
    public static void main (String[] arg) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        Gson gson = new Gson();
        JSONArray jsonArray  = (JSONArray) parser.parse(new FileReader("F://script/qaldjson.json"));

        Type listType = new TypeToken<List<jsonpojo>>() {}.getType();

        List<jsonpojo> list = gson.fromJson(String.valueOf(jsonArray), listType);

        List<jsonpojo.Answer>  answerList = list.get(0).answers;

        List<MyModel> myNewJSON = new ArrayList<MyModel>();
        MyModel mymodelObj;
        MyModel.Parse parseObj;
        List<MyModel.Answer> answerObjList;
        MyModel.Answer answerObj;
        List<Object> empty = new ArrayList<Object>();
        for (jsonpojo myList: list) {
            mymodelObj = new MyModel();
            parseObj = new MyModel.Parse();
            answerObj = new MyModel.Answer();
            answerObjList  = new ArrayList<MyModel.Answer>();
            int id = Integer.parseInt(myList.id) -1;
            mymodelObj.setQuestionId("q" + String.valueOf(id));
          //  System.out.println(myList.id);
            parseObj.setConstraints(empty);
            parseObj.setTopicEntityMid("<http://dbpedia.org/resource/" + myList.getQuery().getSparql().split("res:")[1].split("\\s")[0] + ">");
            parseObj.setTopicEntityName(myList.query.sparql.split("res:")[1].split("\\s")[0]);
          //  parseObj.setInferentialChain(myList.question.get(0).keywords.split("\\,",-1));
            String parsed = null;
            for (jsonpojo.Answer u : myList.getAnswers()) {

                if(u.results.bindings != null) {

                for (jsonpojo.Binding b: u.results.bindings) {


                    MyModel.Answer temp = new MyModel.Answer();
                        if (myList.getAnswertype().equalsIgnoreCase("resource")) {
                            temp.setAnswerType("Entity");
                            temp.setAnswerArgument("<" + b.uri.value + ">");
                            parsed = StringUtils.substringAfter(b.uri.value, "http://dbpedia.org/resource/")
                                    .replaceAll("\\(.*\\)", "");
                            parsed = parsed.replaceAll("[_]", " ");
                            temp.setEntityName(parsed);
                            answerObjList.add(temp);
                        } else if (myList.getAnswertype().equalsIgnoreCase("number")) {
                            temp.setAnswerType("Value");
                            temp.setAnswerArgument("<" + b.c.value + ">");
                            parsed = "";
                            temp.setEntityName(parsed);
                            answerObjList.add(temp);
                        } else if (myList.getAnswertype().equalsIgnoreCase("date")) {
                            temp.setAnswerType("Value");
                            temp.setAnswerArgument("<" + b.date.value + ">");
                            parsed = "";
                            temp.setEntityName(parsed);
                            answerObjList.add(temp);
                        } else if (myList.getAnswertype().equalsIgnoreCase("strng")) {
                            temp.setAnswerType("Value");
                            temp.setAnswerArgument("<" + b.strng.value + ">");
                            parsed = "";
                            temp.setEntityName(parsed);
                            answerObjList.add(temp);
                        } else if (myList.getAnswertype().equalsIgnoreCase("list")) {
                            temp.setAnswerType("Value");
                            temp.setAnswerArgument("<" + b.list.value + ">");
                            parsed = "";
                            temp.setEntityName(parsed);
                            answerObjList.add(temp);
                        }
                    }
                }
                    else {
                    MyModel.Answer temp = new MyModel.Answer();
                            temp.setAnswerType("Value");
                            temp.setAnswerArgument("<"+u.aBoolean+">");
                            parsed = "";
                    temp.setEntityName(parsed);
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
                System.out.println("-----");
            file = new FileWriter("F:/script/queries/q" + i + ".json");
            fileMid = new FileWriter("F:/script/mid/q" + i + ".list");
            file.write(myCustomArray.get(i).toString());
                fileMid.write("q"+i);
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

}
