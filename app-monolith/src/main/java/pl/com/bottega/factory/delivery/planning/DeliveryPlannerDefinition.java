package pl.com.bottega.factory.delivery.planning;

import lombok.*;
import pl.com.bottega.factory.demand.forecasting.Demand;

import java.time.LocalTime;
import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class DeliveryPlannerDefinition {
    @Singular
    Map<Demand.Schema, Map<LocalTime, Double>> definitions;

    public static Map<LocalTime, Double> of(LocalTime time, Double fraction) {
        return Collections.singletonMap(time, fraction);
    }

    public <T> Map<Demand.Schema, T> map(Function<Map<LocalTime, Double>, T> timesAndFractions) {
        return definitions.entrySet().stream().collect(Collectors.toMap(
                Map.Entry::getKey,
                e -> timesAndFractions.apply(e.getValue())
        ));
    }
}