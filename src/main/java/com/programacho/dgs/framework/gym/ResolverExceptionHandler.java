package com.programacho.dgs.framework.gym;

import com.netflix.graphql.dgs.exceptions.DefaultDataFetcherExceptionHandler;
import com.netflix.graphql.types.errors.ErrorType;
import com.netflix.graphql.types.errors.TypedGraphQLError;
import graphql.execution.DataFetcherExceptionHandler;
import graphql.execution.DataFetcherExceptionHandlerParameters;
import graphql.execution.DataFetcherExceptionHandlerResult;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
public class ResolverExceptionHandler implements DataFetcherExceptionHandler {

    private final DefaultDataFetcherExceptionHandler defaultHandler = new DefaultDataFetcherExceptionHandler();

    @Override
    public DataFetcherExceptionHandlerResult onException(final DataFetcherExceptionHandlerParameters handlerParameters) {
        if (handlerParameters.getException() instanceof NoSuchElementException) {
            final TypedGraphQLError graphQLError = TypedGraphQLError.newBuilder()
                    .message("The member is not found.")
                    .location(handlerParameters.getSourceLocation())
                    .path(handlerParameters.getPath())
                    .errorType(ErrorType.NOT_FOUND)
                    .build();

            return DataFetcherExceptionHandlerResult.newResult(graphQLError).build();
        } else {
            return defaultHandler.onException(handlerParameters);
        }
    }
}
