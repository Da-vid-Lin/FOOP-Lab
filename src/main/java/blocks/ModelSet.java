package blocks;

public final class ModelSet extends AbstractBlockModel {
    public ModelSet() { this(BoardSpec.standard(), ScoringPolicy.standard()); }

    public ModelSet(BoardSpec spec) { this(spec, ScoringPolicy.standard()); }

    public ModelSet(BoardSpec spec, ScoringPolicy scoring) {
        super(spec, new SetBoardStorage(), scoring);
    }
}
