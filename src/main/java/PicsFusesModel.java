package main.java;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PicsFusesModel {
    static private PicsFusesModel mInstance = null;
    private Map<String, List<Fuse>> picFusesConfigurations;


    private PicsFusesModel () {
        try {
            Path path = Paths.get(PicsFusesModel.class.getResource("/main/resources/config_pics.json").toURI());
            String jsonFileConent = new String(Files.readAllBytes(path));

            picFusesConfigurations = new HashMap<>();

            JSONObject obj = new JSONObject(jsonFileConent);
            JSONObject picsObject = obj.getJSONObject("pics");
            JSONObject configsObject = obj.getJSONObject("configs");
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




    private class Fuse {
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
