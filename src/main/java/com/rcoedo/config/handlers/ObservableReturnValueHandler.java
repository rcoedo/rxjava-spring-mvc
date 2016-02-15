package com.rcoedo.config.handlers;

import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.context.request.async.WebAsyncUtils;
import org.springframework.web.method.support.AsyncHandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import rx.Observable;

public class ObservableReturnValueHandler implements AsyncHandlerMethodReturnValueHandler {

    @Override
    public boolean isAsyncReturnValue(Object returnValue, MethodParameter returnType) {
        return returnValue != null && supportsReturnType(returnType);
    }

    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        return Observable.class.isAssignableFrom(returnType.getParameterType());
    }

    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest) throws Exception {

        if (returnValue == null) {
            mavContainer.setRequestHandled(true);
            return;
        }

        final Observable<?> observable = Observable.class.cast(returnValue);
        WebAsyncUtils.getAsyncManager(webRequest)
                .startDeferredResultProcessing(new ObservableAdapter<>(observable), mavContainer);
    }

    public class ObservableAdapter<T> extends DeferredResult<T> {
        public ObservableAdapter(Observable<T> observable) {
            observable.subscribe(this::setResult, this::setErrorResult);
        }
    }
}