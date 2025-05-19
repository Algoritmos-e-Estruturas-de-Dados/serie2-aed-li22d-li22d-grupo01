package serie2.problema

import kotlin.collections.HashMap as KotlinHashMap

object ProcessPointsCollection1: PointsCollectionEngine {

     private var HashMap = KotlinHashMap<Point, Source>()

     override fun load(file1: String, file2: String) {

         val readfile1 = reader(file1)
         val readfile2 = reader(file2)

         var line1:String? = readfile1.readLine()
         var line2:String? = readfile2.readLine()

         while ((line1 != null && !line1.startsWith("v"))) {
             line1 = readfile1.readLine()
         }

         while((line2 != null && !line2.startsWith("v"))){
             line2 = readfile2.readLine()
         }


         while (line1 != null || line2 != null){

             if (line1 != null && line1.startsWith("v")){

                 val split = line1.split(' ')
                 if (split.size == 4 && split[0] == "v") {
                     val x = split[2].toDoubleOrNull()
                     val y = split[3].toDoubleOrNull()
                     if (x != null && y != null){
                         val point = Point(x,y)

                         HashMap[point] =
                             when(HashMap[point]){
                                 Source.FILE2 -> Source.BOTH
                                 else -> Source.FILE1
                             }
                     }
                 }
                 line1 = readfile1.readLine()
             }


             if (line2 != null && line2.startsWith("v")){

                 val split = line2.split(' ')
                 if (split.size == 4 && split[0] == "v") {
                     val x = split[2].toDoubleOrNull()
                     val y = split[3].toDoubleOrNull()
                     if (x != null && y != null){
                         val point = Point(x,y)

                         HashMap[point] =
                             when(HashMap[point]){
                                 Source.FILE1 -> Source.BOTH
                                 else -> Source.FILE2
                             }
                     }
                 }
                 line2 = readfile2.readLine()
             }

         }
            readfile1.close()
            readfile2.close()
     }

     override fun union(output: String) {

         val union = writer(output)

         for (point in HashMap){
             union.println("${point.key.x}, ${point.key.y}")
         }
         union.close()
     }

     override fun intersection(output: String) {

         val intersection = writer(output)

         for (point in HashMap){
             if (point.value == Source.BOTH){
                 intersection.println("${point.key.x}, ${point.key.y}")

             }
         }
         intersection.close()
     }

    override fun difference(output: String) {
        val differenceWriter = writer(output)

        for ((point, source) in HashMap) {
            if (source == Source.FILE1) {
                differenceWriter.println("${point.x}, ${point.y}")
            }
        }
        differenceWriter.close()
    }

}