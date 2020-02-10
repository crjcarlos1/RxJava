package com.cralos.application2.example1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.cralos.application2.R;
import com.cralos.application2.example3.activity.Activity3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

// class complete -> https://codingwithmitch.com/courses/rxjava-rxandroid-for-beginners/demo/

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    //vars
    private CompositeDisposable disposables = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startActivity(new Intent(this, Activity3.class));
        //ejercicio15();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposables.clear();
    }

    private void ejercicio1() {
        Observable<Task> taskObservable = Observable
                .fromIterable(DataSource.createTaskList())
                .subscribeOn(Schedulers.io())
                .filter(task -> {
                    Log.e(TAG, "filter: " + Thread.currentThread().getName());
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                    }
                    if (task.isComplete())
                        return true;
                    else
                        return false;
                })
                .observeOn(AndroidSchedulers.mainThread());

        taskObservable.subscribe(new Observer<Task>() {
            @Override
            public void onSubscribe(Disposable disposable) {
                disposables.add(disposable);
                Log.e(TAG, "onSubscribe: called");
            }

            @Override
            public void onNext(Task task) {
                Log.e(TAG, "onNext: Hilo-> " + Thread.currentThread().getName() + ", description: " + task.getDescription());
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: " + e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.e(TAG, "onComplete: called");
            }
        });
    }

    private void ejercicio2() {
        final Task task = new Task("walk the dog", false, 3);
        //List<Task> tasks = DataSource.createTaskList();

        Observable<Task> taskObservable = Observable
                .just(task)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        taskObservable.subscribe(new Observer<Task>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Task task) {
                Log.e(TAG, "onNext: " + task.getDescription());
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

    }

    private void ejercicio3() {
        Observable<Integer> observable = Observable
                .range(0, 9)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        observable.subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer integer) {
                Log.e(TAG, "onNext: " + integer);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    private void ejercicio4() {

        Observable<Task> observable = Observable
                .range(0, 5)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(myNumber -> new Task("Mi nueva tarea", false, myNumber))
                .takeWhile(task -> task.getPriority() < 3);

        observable.subscribe(new Observer<Task>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Task task) {
                Log.e(TAG, "onNext: " + task.getPriority());
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

    }

    private void ejercicio5() {
        Integer numeros[] = {1, 2, 3, 4, 5};
        Observable<Integer> observable = Observable
                .fromArray(numeros)
                .filter(numero -> numero < 3)
                .repeat(2)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        observable.subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e(TAG, "onSubscribe: ");
            }

            @Override
            public void onNext(Integer integer) {
                Log.e(TAG, "onNext: " + integer);
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: ");
            }

            @Override
            public void onComplete() {
                Log.e(TAG, "onComplete: ");
            }
        });

    }

    private void ejercicio6() {
        //emit an observable every time interval
        Observable<Long> observable = Observable
                .interval(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .takeWhile(myLong -> myLong <= 5)
                .observeOn(AndroidSchedulers.mainThread());

        observable.subscribe(new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Long aLong) {
                Log.e(TAG, "onNext: " + aLong);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

    }

    private void ejercicio7() {
        //emit a single observable after a given delay
        Observable<Long> observable = Observable
                .timer(3, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        observable.subscribe(new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Long aLong) {
                Log.e(TAG, "onNext: " + aLong);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    private void ejercicio8() {
        Task[] tasks = new Task[5];
        tasks[0] = new Task("description 0", true, 1);
        tasks[1] = new Task("description 1", true, 2);
        tasks[2] = new Task("description 2", true, 3);
        tasks[3] = new Task("description 3", false, 4);
        tasks[4] = new Task("description 4", true, 5);

        Observable<Task> observable = Observable
                .fromArray(tasks)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        observable.subscribe(new Observer<Task>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Task task) {
                Log.e(TAG, "onNext: " + task.getDescription());
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });


    }

    private void ejercicio9() {
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task("Description 0", true, 1));
        tasks.add(new Task("Description 1", true, 1));
        tasks.add(new Task("Description 2", true, 1));
        tasks.add(new Task("Description 3", true, 1));
        tasks.add(new Task("Description 4", true, 1));

        Observable<Task> observable = Observable
                .fromIterable(tasks)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        observable.subscribe(new Observer<Task>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Task task) {
                Log.e(TAG, "onNext: " + task.getDescription());
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

    }

    private void ejercicio10() {
        Observable<Task> observable = Observable
                .fromIterable(DataSource.createTaskList())
                .subscribeOn(Schedulers.io())
                .filter(task -> task.getDescription().equals("Make me bed"))
                .observeOn(AndroidSchedulers.mainThread());

        observable.subscribe(new Observer<Task>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Task task) {
                Log.e(TAG, "onNext: " + task.getDescription());
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    private void ejercicio11() {
        List<Task> lista = DataSource.createTaskList();
        lista.add(lista.get(lista.size() - 1));

        Observable<Task> observable = Observable
                .fromIterable(lista)
                .subscribeOn(Schedulers.io())
                .distinct(task -> task.getDescription())
                .observeOn(AndroidSchedulers.mainThread());

        observable.subscribe(new Observer<Task>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Task task) {
                Log.e(TAG, "onNext: " + task.getDescription());
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

    }

    private void ejercicio12() {
        Observable<Task> observable = Observable
                .fromIterable(DataSource.createTaskList())
                .take(4)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        observable.subscribe(new Observer<Task>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Task task) {
                Log.e(TAG, "onNext: " + task.getDescription());
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    private void ejercicio13() {
        Observable<Task> observable = Observable
                .fromIterable(DataSource.createTaskList())
                .takeWhile(task -> task.isComplete())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        observable.subscribe(new Observer<Task>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Task task) {
                Log.e("rxjava", "onNext: " + task.getDescription());
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    private void ejercicio14() {
        Observable<String> observable = Observable
                .fromIterable(DataSource.createTaskList())
                .subscribeOn(Schedulers.io())
                .map(task -> "Description: " + task.getDescription())
                .observeOn(AndroidSchedulers.mainThread());

        observable.subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                Log.e("rxjava", "onNext: " + s);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    private void ejercicio15() {
        Observable<List<Task>> observable = Observable
                .fromIterable(DataSource.createTaskList())
                .buffer(2)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        observable.subscribe(new Observer<List<Task>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<Task> tasks) {
                for (Task task : tasks) {
                    Log.e("rxjava", "task: " + task.getDescription());
                }
                Log.e("rxjava", "+++++++++++++++++++++++++++++++++++++");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

}
