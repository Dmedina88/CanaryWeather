package inc.grayherring.com.canaryweather

import androidx.lifecycle.*

class TestObserver<T>(val observerTill: Int, val handler: (List<T>) -> Unit) : Observer<T>, LifecycleOwner {

    private val values = mutableListOf<T>()
    private val lifecycle = LifecycleRegistry(this)

    init {
        lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
    }

    override fun getLifecycle(): Lifecycle = lifecycle


    override fun onChanged(t: T) {
        values.add(t)
        println(values.size.toString() + " " + t.toString())
        if (values.size == observerTill) {
            handler(values)
            lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        }
    }
}

fun <T> LiveData<T>.testObserver(observerTill: Int, handler: (List<T>) -> Unit): TestObserver<T> {
    val testObserver = TestObserver(observerTill, handler)
    this.observe(testObserver, testObserver)
    return testObserver
}