package com.arunsoorya.timeline.presenter;

import android.content.Context;

public abstract class BasePresenter<T> {
  /**
   * The View.
   */
  T view;
  /**
   * The Context.
   */
  protected Context context;

  public BasePresenter(T t) {
    context = (Context) t;
  }

//  /**
//   * On attach.
//   *
//   * @param view    the view
//   * @param context the context
//   */
//  public void onAttach(T view,Context context){
//    this.view = view;
//    this.context = context;
//  }

  /**
   * On detach.
   */
  public void onDetach(){
    this.view = null;
  }

  /**
   * Gets view.
   *
   * @return the view
   */
  public T getView() {
    return view;
  }


}
