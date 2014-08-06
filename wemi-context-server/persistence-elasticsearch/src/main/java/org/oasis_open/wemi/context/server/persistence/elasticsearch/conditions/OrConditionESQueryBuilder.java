package org.oasis_open.wemi.context.server.persistence.elasticsearch.conditions;

import org.elasticsearch.index.query.FilterBuilder;
import org.elasticsearch.index.query.FilterBuilders;
import org.oasis_open.wemi.context.server.api.conditions.Condition;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by loom on 06.08.14.
 */
public class OrConditionESQueryBuilder implements ESQueryBuilder {

    public OrConditionESQueryBuilder() {
    }

    public FilterBuilder buildFilter(Condition condition, ConditionESQueryBuilderDispatcher dispatcher) {
        List<Condition> conditions = (List<Condition>) condition.getParameterValues().get("subConditions");

        List<FilterBuilder> l = new ArrayList<FilterBuilder>();
        for (Object sub : conditions) {
            l.add(dispatcher.buildFilter((Condition) sub));
        }
        return FilterBuilders.orFilter(l.toArray(new FilterBuilder[l.size()]));
    }
}
