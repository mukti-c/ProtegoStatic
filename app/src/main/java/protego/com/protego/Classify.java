package protego.com.protego;


import java.io.File;
import java.io.IOException;

import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayesUpdateable;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ArffLoader;

/**
 * Created by 1MaV3RiCk on 15/01/15.
 */
public class Classify {

    NaiveBayesUpdateable nbu = new NaiveBayesUpdateable();

    //Checking git commit
    public int Train (String fname) {

        int flag = 1;

        ArffLoader loader = new ArffLoader();
        Instances structure = null;

        try {
            loader.setFile(new File("/sdcard/"+fname+".arff"));
            structure = loader.getStructure();
            structure.setClassIndex(structure.numAttributes() - 1);
        } catch (IOException e) {
            flag = 0;
            e.printStackTrace();
        }

        Instance current;

        try {
            nbu.buildClassifier(structure);
            while ((current = loader.getNextInstance(structure)) != null)
                nbu.updateClassifier(current);
        } catch (Exception e) {
            flag = -1;
            e.printStackTrace();
        }

        return flag;
    }

    public String Evaluate (String fname) {

        String [] options = new String[2];
        options[0] = "-t";
        options[1] = "/sdcard/"+fname+".csv";

        String out = null;

        try {
            out = Evaluation.evaluateModel(nbu, options);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return out;
    }
}