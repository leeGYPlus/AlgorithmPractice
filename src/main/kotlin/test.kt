


fun showMessage(){
    println("")
}

// 扩展函数
fun MutableList<Int>.exchange(fromIndex: Int, toIndex: Int) {
    val temp = this[fromIndex]
    this[fromIndex] = this[toIndex]
    this[toIndex] = temp
}

class Bean{
    var age:Int = 0
    fun info():Int = age
}

lateinit var valueTrue:String
var Bean.name:String
    get(){
        if(valueTrue == ""){
            return "dfdf"
        }else{
            return valueTrue
        }
    }
    set(value) {
        valueTrue = value
    }


fun main(args: Array<String>) {
    val bean = Bean()
    bean.name = ""
    println(bean.name)
    bean.name= "fdfdf"
    println(bean.name)
}