/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewsHandler;

import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;

/**
 *
 * @author António Silva
 */
public class ViewExpiredExceptionExceptionHandlerFactory extends ExceptionHandlerFactory {

    private ExceptionHandlerFactory parent;

    public ViewExpiredExceptionExceptionHandlerFactory(ExceptionHandlerFactory parent) {
        this.parent = parent;
    }

    @Override
    public ExceptionHandler getExceptionHandler() {
        ExceptionHandler result = parent.getExceptionHandler();
        result = new ViewExpiredExceptionExceptionHandler(result);

        return result;
    }

}
