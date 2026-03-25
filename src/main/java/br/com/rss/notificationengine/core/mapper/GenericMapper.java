package br.com.rss.notificationengine.core.mapper;

import java.util.Collections;
import java.util.List;

import static java.util.Objects.isNull;

/**
 * Interface genérica para mapeamento entre tipos.
 *
 * @param <I> Input (Entrada - ex: DTO)
 * @param <O> Output (Saída - ex: Domain/Entity)
 */
public interface GenericMapper<I, O> {
    O map(I input);

    default I mapBack(O domain) {
        throw new UnsupportedOperationException("Conversão de volta não implementada.");
    }

    default List<O> toList(List<I> inputs) {
        if (isNull(inputs)) {
            return Collections.emptyList();
        }
        return inputs.stream().map(this::map).toList();
    }

    default List<I> toListBack(List<O> outputs) {
        if (isNull(outputs)) {
            return Collections.emptyList();
        }
        return outputs.stream().map(this::mapBack).toList();
    }
}
