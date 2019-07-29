package main.java;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PicsFusesModel {
    static private PicsFusesModel mInstance = null;
    private Map<String, String> picsConfigsMap;
    private Map<String, List<Fuse>> configsFusesMap;


    private PicsFusesModel () {
        try {
            URI uri = getClass().getResource("/config_pics.json").toURI();
            System.out.println(uri);
            Path path = Paths.get(uri);
            String jsonFileConent = new String(Files.readAllBytes(path));

            picsConfigsMap = new HashMap<>();
            configsFusesMap = new HashMap<>();

            JSONObject obj = new JSONObject(jsonFileConent);
            JSONObject picsObject = obj.getJSONObject("pics");
            JSONObject configsObject = obj.getJSONObject("configs");


            for (String key : picsObject.keySet()) {
                picsConfigsMap.put(key, picsObject.getString(key));
            }

            for (String key : configsObject.keySet()) {
                JSONArray fusesArray =  configsObject.getJSONArray(key);

                List<Fuse> fusesList = new ArrayList<>();
                for (int i = 0; i < fusesArray.length(); i++) {
                    JSONObject fuseObject = fusesArray.getJSONObject(i);


                    JSONArray valuesArray = fuseObject.getJSONArray("values");
                    List<String> valuesList = new ArrayList<>();
                    for (int j = 0; j < valuesArray.length(); j++) {
                        valuesList.add(valuesArray.getString(j));
                    }

                    fusesList.add(new Fuse(fuseObject.getString("name"),
                            fuseObject.getString("description"),
                            fuseObject.getInt("word"),
                            fuseObject.getInt("offset"),
                            fuseObject.getInt("bits"),
                            valuesList));
                }

                configsFusesMap.put(key, fusesList);
            }
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
        catch (URISyntaxException use) {
            use.printStackTrace();
        }

    }

    static public PicsFusesModel getInstance () {
        if (mInstance == null)
            mInstance = new PicsFusesModel();

        return mInstance;
    }


    public List<String> getPics () {
        List<String> picsList = new ArrayList<>(picsConfigsMap.size());

        for (String key : picsConfigsMap.keySet()) {
            picsList.add(key);
        }

        return picsList;
    }


    public List<Fuse> getPicFuses (String pic) {
        String config = picsConfigsMap.get(pic);

        if (config != null) {
            return configsFusesMap.get(config);
        }

        return null;
    }


    public class Fuse {
        private String name;
        private String description;
        private int word;
        private int offset;
        private int bits;
        private List<String> values;


        public Fuse (String name, String description, int word, int offset, int bits, List<String> values) {
            this.name = name;
            this.description = description;
            this.word = word;
            this.offset = offset;
            this.bits = bits;
            this.values = values;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getWord() {
            return word;
        }

        public void setWord(int word) {
            this.word = word;
        }

        public int getOffset() {
            return offset;
        }

        public void setOffset(int offset) {
            this.offset = offset;
        }

        public int getBits() {
            return bits;
        }

        public void setBits(int bits) {
            this.bits = bits;
        }

        public List<String> getValues() {
            return values;
        }

        public void setValues(List<String> values) {
            this.values = values;
        }
    }
}
