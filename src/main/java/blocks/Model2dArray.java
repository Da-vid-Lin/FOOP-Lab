package blocks;

public final class Model2dArray extends AbstractBlockModel {
    public Model2dArray() { this(BoardSpec.standard(), ScoringPolicy.standard()); }

    public Model2dArray(BoardSpec spec) { this(spec, ScoringPolicy.standard()); }

    public Model2dArray(BoardSpec spec, ScoringPolicy scoring) {
        super(spec, new ArrayBoardStorage(spec), scoring);
    }
}
