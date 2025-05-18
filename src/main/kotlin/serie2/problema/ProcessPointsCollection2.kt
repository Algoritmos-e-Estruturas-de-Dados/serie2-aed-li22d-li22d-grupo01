package serie2.problema

import serie2.part4.AEDHashMap as AEDHashMap

object ProcessPointsCollection2:PointsCollectionEngine {

    private var HashMap = AEDHashMap<Point, Source>()

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
                        val pointSource = HashMap[Point(x,y)]
                        val point = Point(x,y)
                        val new =
                            when(pointSource){
                                Source.FILE2 -> Source.BOTH
                                else -> Source.FILE1
                            }
                        HashMap.put(point, new)
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
                        val pointSource = HashMap[Point(x,y)]
                        val point = Point(x,y)
                        val new =
                            when(pointSource){
                                Source.FILE1 -> Source.BOTH
                                else -> Source.FILE2
                            }
                        HashMap.put(point, new)
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

        val difference = writer(output)

        for (point in HashMap){
            if ((point.value == Source.FILE1 && !(point.value == Source.FILE2)) || (!(point.value == Source.FILE1) && (point.value == Source.FILE2))){
                difference.println("${point.key.x}, ${point.key.y}")
            }
        }
        difference.close()
    }
}