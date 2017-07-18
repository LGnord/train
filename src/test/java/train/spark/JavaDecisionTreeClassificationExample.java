package train.spark;

//$example on$
import java.util.HashMap;
import java.util.Map;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.mllib.regression.LabeledPoint;
import org.apache.spark.mllib.tree.DecisionTree;
import org.apache.spark.mllib.tree.model.DecisionTreeModel;
import org.apache.spark.mllib.util.MLUtils;

import scala.Tuple2;
//$example off$

// format du fichier
// classe = mission la plus chère
// 1-10:  mes premiers coups dans l'ordre
// 11-20: ses premiers coups dans l'ordre 
// 20-30: nb de cartes connues piochiées: (20) GRAY, (21) YELLOW, (22) BLUE,  (23) BLACK, (24) ORANGE, 
//                                        (25) RED,  (26) PINK,   (27) GREEN, (28) WHITE, (29) JOCKER
class JavaDecisionTreeClassificationExample {

	public static void main(String[] args) {

		System.setProperty("hadoop.home.dir", "C:\\Users\\UTILISATEUR\\Downloads");

		// $example on$
		SparkConf sparkConf = new SparkConf().setAppName("JavaDecisionTreeClassificationExample").setMaster("local");
		JavaSparkContext jsc = new JavaSparkContext(sparkConf);

		// Load and parse the data file.
		String datapath = "data/train/parties.txt";
		JavaRDD<LabeledPoint> data = MLUtils.loadLibSVMFile(jsc.sc(), datapath).toJavaRDD();
		// Split the data into training and test sets (30% held out for testing)
		JavaRDD<LabeledPoint>[] splits = data.randomSplit(new double[] { 0.7, 0.3 });
		JavaRDD<LabeledPoint> trainingData = splits[0];
		JavaRDD<LabeledPoint> testData = splits[1];

		// Set parameters.
		// Empty categoricalFeaturesInfo indicates all features are continuous.
		int numClasses = 30;
		Map<Integer, Integer> categoricalFeaturesInfo = new HashMap<>();
		String impurity = "gini";
		int maxDepth = 5;
		int maxBins = 32;

		// Train a DecisionTree model for classification.
		DecisionTreeModel model = DecisionTree.trainClassifier(trainingData, numClasses, categoricalFeaturesInfo,
				impurity, maxDepth, maxBins);

		// Evaluate model on test instances and compute test error
		JavaPairRDD<Double, Double> predictionAndLabel = testData
				.mapToPair(p -> new Tuple2<>(model.predict(p.features()), p.label()));
		double testErr = predictionAndLabel.filter(pl -> !pl._1().equals(pl._2())).count() / (double) testData.count();

		System.out.println("Test Error: " + testErr);
		System.out.println("Learned classification tree model:\n" + model.toDebugString());

		// Save and load model
		try {
			model.save(jsc.sc(), "target/tmp/myDecisionTreeClassificationModel");
		} catch (Exception e) {
			e.printStackTrace();
		}
		// DecisionTreeModel sameModel = DecisionTreeModel.load(jsc.sc(),
		// "target/tmp/myDecisionTreeClassificationModel");
		// Vector features = Vectors.sparse(435, new int[] { 0, 2 }, new
		// double[] { 1.0, 3.0 });

		// System.out.println("+Predict: " + model.predict(features));
	}
}