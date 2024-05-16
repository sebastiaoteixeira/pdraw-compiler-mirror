// Generated from /home/rmachado/uni/pdraw-t12/src/pdraw.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class pdrawParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, T__32=33, Property=34, Color=35, String=36, Integer=37, Real=38, 
		ID=39, COMMENT=40, WS=41;
	public static final int
		RULE_program = 0, RULE_statement = 1, RULE_define = 2, RULE_penDefinition = 3, 
		RULE_propertyDefinition = 4, RULE_canvasDefinition = 5, RULE_declaration = 6, 
		RULE_execution = 7, RULE_pause = 8, RULE_stdout = 9, RULE_expression = 10, 
		RULE_point = 11;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "statement", "define", "penDefinition", "propertyDefinition", 
			"canvasDefinition", "declaration", "execution", "pause", "stdout", "expression", 
			"point"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "';'", "'define'", "'pen'", "'{'", "'}'", "'='", "'canvas'", "'real'", 
			"'int'", "'string'", "'point'", "'execute'", "'pause'", "'->'", "'stdout'", 
			"'new'", "'+'", "'-'", "'\\u00BA'", "'*'", "'/'", "'//'", "'%'", "'('", 
			"')'", "'down'", "'up'", "'forward'", "'left'", "'right'", "'<-'", "'stdin'", 
			"','"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, "Property", 
			"Color", "String", "Integer", "Real", "ID", "COMMENT", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "pdraw.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public pdrawParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgramContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(pdrawParser.EOF, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(27);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1035104370444L) != 0)) {
				{
				{
				setState(24);
				statement();
				}
				}
				setState(29);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(30);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StatementContext extends ParserRuleContext {
		public DefineContext define() {
			return getRuleContext(DefineContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public DeclarationContext declaration() {
			return getRuleContext(DeclarationContext.class,0);
		}
		public StdoutContext stdout() {
			return getRuleContext(StdoutContext.class,0);
		}
		public PauseContext pause() {
			return getRuleContext(PauseContext.class,0);
		}
		public ExecutionContext execution() {
			return getRuleContext(ExecutionContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_statement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(38);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				{
				setState(32);
				define();
				}
				break;
			case 2:
				{
				setState(33);
				expression(0);
				}
				break;
			case 3:
				{
				setState(34);
				declaration();
				}
				break;
			case 4:
				{
				setState(35);
				stdout();
				}
				break;
			case 5:
				{
				setState(36);
				pause();
				}
				break;
			case 6:
				{
				setState(37);
				execution();
				}
				break;
			}
			setState(40);
			match(T__0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DefineContext extends ParserRuleContext {
		public PenDefinitionContext penDefinition() {
			return getRuleContext(PenDefinitionContext.class,0);
		}
		public CanvasDefinitionContext canvasDefinition() {
			return getRuleContext(CanvasDefinitionContext.class,0);
		}
		public DefineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_define; }
	}

	public final DefineContext define() throws RecognitionException {
		DefineContext _localctx = new DefineContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_define);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(42);
			match(T__1);
			setState(45);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__2:
				{
				setState(43);
				penDefinition();
				}
				break;
			case T__6:
				{
				setState(44);
				canvasDefinition();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PenDefinitionContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(pdrawParser.ID, 0); }
		public List<PropertyDefinitionContext> propertyDefinition() {
			return getRuleContexts(PropertyDefinitionContext.class);
		}
		public PropertyDefinitionContext propertyDefinition(int i) {
			return getRuleContext(PropertyDefinitionContext.class,i);
		}
		public PenDefinitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_penDefinition; }
	}

	public final PenDefinitionContext penDefinition() throws RecognitionException {
		PenDefinitionContext _localctx = new PenDefinitionContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_penDefinition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(47);
			match(T__2);
			setState(48);
			match(ID);
			setState(49);
			match(T__3);
			setState(53);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Property) {
				{
				{
				setState(50);
				propertyDefinition();
				}
				}
				setState(55);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(56);
			match(T__4);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PropertyDefinitionContext extends ParserRuleContext {
		public TerminalNode Property() { return getToken(pdrawParser.Property, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public PropertyDefinitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_propertyDefinition; }
	}

	public final PropertyDefinitionContext propertyDefinition() throws RecognitionException {
		PropertyDefinitionContext _localctx = new PropertyDefinitionContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_propertyDefinition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(58);
			match(Property);
			setState(59);
			match(T__5);
			setState(60);
			expression(0);
			setState(61);
			match(T__0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CanvasDefinitionContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(pdrawParser.ID, 0); }
		public TerminalNode String() { return getToken(pdrawParser.String, 0); }
		public PointContext point() {
			return getRuleContext(PointContext.class,0);
		}
		public CanvasDefinitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_canvasDefinition; }
	}

	public final CanvasDefinitionContext canvasDefinition() throws RecognitionException {
		CanvasDefinitionContext _localctx = new CanvasDefinitionContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_canvasDefinition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(63);
			match(T__6);
			setState(64);
			match(ID);
			setState(65);
			match(String);
			setState(66);
			point();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DeclarationContext extends ParserRuleContext {
		public Token type;
		public TerminalNode ID() { return getToken(pdrawParser.ID, 0); }
		public DeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaration; }
	}

	public final DeclarationContext declaration() throws RecognitionException {
		DeclarationContext _localctx = new DeclarationContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_declaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(68);
			((DeclarationContext)_localctx).type = _input.LT(1);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 3848L) != 0)) ) {
				((DeclarationContext)_localctx).type = (Token)_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(69);
			match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExecutionContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ExecutionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_execution; }
	}

	public final ExecutionContext execution() throws RecognitionException {
		ExecutionContext _localctx = new ExecutionContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_execution);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(71);
			match(T__11);
			setState(72);
			expression(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PauseContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public PauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pause; }
	}

	public final PauseContext pause() throws RecognitionException {
		PauseContext _localctx = new PauseContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_pause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(74);
			match(T__12);
			setState(75);
			expression(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StdoutContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public StdoutContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stdout; }
	}

	public final StdoutContext stdout() throws RecognitionException {
		StdoutContext _localctx = new StdoutContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_stdout);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(77);
			expression(0);
			setState(78);
			match(T__13);
			setState(79);
			match(T__14);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExpressionContext extends ParserRuleContext {
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
	 
		public ExpressionContext() { }
		public void copyFrom(ExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprToStringContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ExprToStringContext(ExpressionContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprStringContext extends ExpressionContext {
		public TerminalNode String() { return getToken(pdrawParser.String, 0); }
		public ExprStringContext(ExpressionContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprParentContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ExprParentContext(ExpressionContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprPointContext extends ExpressionContext {
		public PointContext point() {
			return getRuleContext(PointContext.class,0);
		}
		public ExprPointContext(ExpressionContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprStringConcatContext extends ExpressionContext {
		public TerminalNode String() { return getToken(pdrawParser.String, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ExprStringConcatContext(ExpressionContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprToIntContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ExprToIntContext(ExpressionContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprToRealContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ExprToRealContext(ExpressionContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprPenOperatorContext extends ExpressionContext {
		public Token op;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ExprPenOperatorContext(ExpressionContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprMultDivModContext extends ExpressionContext {
		public Token op;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ExprMultDivModContext(ExpressionContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprAddSubContext extends ExpressionContext {
		public Token op;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ExprAddSubContext(ExpressionContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprSetPropertyContext extends ExpressionContext {
		public Token op;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode Property() { return getToken(pdrawParser.Property, 0); }
		public ExprSetPropertyContext(ExpressionContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprConvToRadContext extends ExpressionContext {
		public Token op;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ExprConvToRadContext(ExpressionContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprPenUnaryContext extends ExpressionContext {
		public Token op;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ExprPenUnaryContext(ExpressionContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprUnaryContext extends ExpressionContext {
		public Token op;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ExprUnaryContext(ExpressionContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprNewContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ExprNewContext(ExpressionContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprStdinContext extends ExpressionContext {
		public TerminalNode String() { return getToken(pdrawParser.String, 0); }
		public ExprStdinContext(ExpressionContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprAssignContext extends ExpressionContext {
		public Token op;
		public TerminalNode ID() { return getToken(pdrawParser.ID, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ExprAssignContext(ExpressionContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprRealContext extends ExpressionContext {
		public TerminalNode Real() { return getToken(pdrawParser.Real, 0); }
		public ExprRealContext(ExpressionContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprIntegerContext extends ExpressionContext {
		public TerminalNode Integer() { return getToken(pdrawParser.Integer, 0); }
		public ExprIntegerContext(ExpressionContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprIdContext extends ExpressionContext {
		public TerminalNode ID() { return getToken(pdrawParser.ID, 0); }
		public ExprIdContext(ExpressionContext ctx) { copyFrom(ctx); }
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 20;
		enterRecursionRule(_localctx, 20, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(119);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				{
				_localctx = new ExprNewContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(82);
				match(T__15);
				setState(84);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
				case 1:
					{
					setState(83);
					expression(0);
					}
					break;
				}
				}
				break;
			case 2:
				{
				_localctx = new ExprUnaryContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(86);
				((ExprUnaryContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==T__16 || _la==T__17) ) {
					((ExprUnaryContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(87);
				expression(19);
				}
				break;
			case 3:
				{
				_localctx = new ExprStringConcatContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(88);
				match(String);
				setState(89);
				expression(15);
				}
				break;
			case 4:
				{
				_localctx = new ExprToIntContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(90);
				match(T__8);
				setState(91);
				match(T__23);
				setState(92);
				expression(0);
				setState(93);
				match(T__24);
				}
				break;
			case 5:
				{
				_localctx = new ExprToRealContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(95);
				match(T__7);
				setState(96);
				match(T__23);
				setState(97);
				expression(0);
				setState(98);
				match(T__24);
				}
				break;
			case 6:
				{
				_localctx = new ExprToStringContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(100);
				match(T__9);
				setState(101);
				match(T__23);
				setState(102);
				expression(0);
				setState(103);
				match(T__24);
				}
				break;
			case 7:
				{
				_localctx = new ExprStdinContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(105);
				match(T__31);
				setState(106);
				match(String);
				}
				break;
			case 8:
				{
				_localctx = new ExprAssignContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(107);
				match(ID);
				setState(108);
				((ExprAssignContext)_localctx).op = match(T__5);
				setState(109);
				expression(7);
				}
				break;
			case 9:
				{
				_localctx = new ExprIdContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(110);
				match(ID);
				}
				break;
			case 10:
				{
				_localctx = new ExprIntegerContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(111);
				match(Integer);
				}
				break;
			case 11:
				{
				_localctx = new ExprRealContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(112);
				match(Real);
				}
				break;
			case 12:
				{
				_localctx = new ExprStringContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(113);
				match(String);
				}
				break;
			case 13:
				{
				_localctx = new ExprPointContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(114);
				point();
				}
				break;
			case 14:
				{
				_localctx = new ExprParentContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(115);
				match(T__23);
				setState(116);
				expression(0);
				setState(117);
				match(T__24);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(140);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(138);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
					case 1:
						{
						_localctx = new ExprMultDivModContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(121);
						if (!(precpred(_ctx, 17))) throw new FailedPredicateException(this, "precpred(_ctx, 17)");
						setState(122);
						((ExprMultDivModContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 15728640L) != 0)) ) {
							((ExprMultDivModContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(123);
						expression(18);
						}
						break;
					case 2:
						{
						_localctx = new ExprAddSubContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(124);
						if (!(precpred(_ctx, 16))) throw new FailedPredicateException(this, "precpred(_ctx, 16)");
						setState(125);
						((ExprAddSubContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__16 || _la==T__17) ) {
							((ExprAddSubContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(126);
						expression(17);
						}
						break;
					case 3:
						{
						_localctx = new ExprPenOperatorContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(127);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(128);
						((ExprPenOperatorContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 1879048192L) != 0)) ) {
							((ExprPenOperatorContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(129);
						expression(11);
						}
						break;
					case 4:
						{
						_localctx = new ExprSetPropertyContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(130);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(131);
						((ExprSetPropertyContext)_localctx).op = match(T__30);
						setState(132);
						match(Property);
						setState(133);
						expression(10);
						}
						break;
					case 5:
						{
						_localctx = new ExprConvToRadContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(134);
						if (!(precpred(_ctx, 18))) throw new FailedPredicateException(this, "precpred(_ctx, 18)");
						setState(135);
						((ExprConvToRadContext)_localctx).op = match(T__18);
						}
						break;
					case 6:
						{
						_localctx = new ExprPenUnaryContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(136);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(137);
						((ExprPenUnaryContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__25 || _la==T__26) ) {
							((ExprPenUnaryContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						}
						break;
					}
					} 
				}
				setState(142);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PointContext extends ParserRuleContext {
		public ExpressionContext x;
		public ExpressionContext y;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public PointContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_point; }
	}

	public final PointContext point() throws RecognitionException {
		PointContext _localctx = new PointContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_point);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(143);
			match(T__23);
			setState(144);
			((PointContext)_localctx).x = expression(0);
			setState(145);
			match(T__32);
			setState(146);
			((PointContext)_localctx).y = expression(0);
			setState(147);
			match(T__24);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 10:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 17);
		case 1:
			return precpred(_ctx, 16);
		case 2:
			return precpred(_ctx, 10);
		case 3:
			return precpred(_ctx, 9);
		case 4:
			return precpred(_ctx, 18);
		case 5:
			return precpred(_ctx, 11);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001)\u0096\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0001"+
		"\u0000\u0005\u0000\u001a\b\u0000\n\u0000\f\u0000\u001d\t\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0003\u0001\'\b\u0001\u0001\u0001\u0001\u0001\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0003\u0002.\b\u0002\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0005\u00034\b\u0003\n\u0003\f\u00037\t\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\b\u0001\b\u0001\b\u0001\t\u0001\t\u0001\t\u0001\t\u0001\n\u0001"+
		"\n\u0001\n\u0003\nU\b\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0003\nx\b\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0005\n\u008b\b\n\n\n\f\n\u008e\t\n\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0000\u0001"+
		"\u0014\f\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0000"+
		"\u0005\u0002\u0000\u0003\u0003\b\u000b\u0001\u0000\u0011\u0012\u0001\u0000"+
		"\u0014\u0017\u0001\u0000\u001c\u001e\u0001\u0000\u001a\u001b\u00a5\u0000"+
		"\u001b\u0001\u0000\u0000\u0000\u0002&\u0001\u0000\u0000\u0000\u0004*\u0001"+
		"\u0000\u0000\u0000\u0006/\u0001\u0000\u0000\u0000\b:\u0001\u0000\u0000"+
		"\u0000\n?\u0001\u0000\u0000\u0000\fD\u0001\u0000\u0000\u0000\u000eG\u0001"+
		"\u0000\u0000\u0000\u0010J\u0001\u0000\u0000\u0000\u0012M\u0001\u0000\u0000"+
		"\u0000\u0014w\u0001\u0000\u0000\u0000\u0016\u008f\u0001\u0000\u0000\u0000"+
		"\u0018\u001a\u0003\u0002\u0001\u0000\u0019\u0018\u0001\u0000\u0000\u0000"+
		"\u001a\u001d\u0001\u0000\u0000\u0000\u001b\u0019\u0001\u0000\u0000\u0000"+
		"\u001b\u001c\u0001\u0000\u0000\u0000\u001c\u001e\u0001\u0000\u0000\u0000"+
		"\u001d\u001b\u0001\u0000\u0000\u0000\u001e\u001f\u0005\u0000\u0000\u0001"+
		"\u001f\u0001\u0001\u0000\u0000\u0000 \'\u0003\u0004\u0002\u0000!\'\u0003"+
		"\u0014\n\u0000\"\'\u0003\f\u0006\u0000#\'\u0003\u0012\t\u0000$\'\u0003"+
		"\u0010\b\u0000%\'\u0003\u000e\u0007\u0000& \u0001\u0000\u0000\u0000&!"+
		"\u0001\u0000\u0000\u0000&\"\u0001\u0000\u0000\u0000&#\u0001\u0000\u0000"+
		"\u0000&$\u0001\u0000\u0000\u0000&%\u0001\u0000\u0000\u0000\'(\u0001\u0000"+
		"\u0000\u0000()\u0005\u0001\u0000\u0000)\u0003\u0001\u0000\u0000\u0000"+
		"*-\u0005\u0002\u0000\u0000+.\u0003\u0006\u0003\u0000,.\u0003\n\u0005\u0000"+
		"-+\u0001\u0000\u0000\u0000-,\u0001\u0000\u0000\u0000.\u0005\u0001\u0000"+
		"\u0000\u0000/0\u0005\u0003\u0000\u000001\u0005\'\u0000\u000015\u0005\u0004"+
		"\u0000\u000024\u0003\b\u0004\u000032\u0001\u0000\u0000\u000047\u0001\u0000"+
		"\u0000\u000053\u0001\u0000\u0000\u000056\u0001\u0000\u0000\u000068\u0001"+
		"\u0000\u0000\u000075\u0001\u0000\u0000\u000089\u0005\u0005\u0000\u0000"+
		"9\u0007\u0001\u0000\u0000\u0000:;\u0005\"\u0000\u0000;<\u0005\u0006\u0000"+
		"\u0000<=\u0003\u0014\n\u0000=>\u0005\u0001\u0000\u0000>\t\u0001\u0000"+
		"\u0000\u0000?@\u0005\u0007\u0000\u0000@A\u0005\'\u0000\u0000AB\u0005$"+
		"\u0000\u0000BC\u0003\u0016\u000b\u0000C\u000b\u0001\u0000\u0000\u0000"+
		"DE\u0007\u0000\u0000\u0000EF\u0005\'\u0000\u0000F\r\u0001\u0000\u0000"+
		"\u0000GH\u0005\f\u0000\u0000HI\u0003\u0014\n\u0000I\u000f\u0001\u0000"+
		"\u0000\u0000JK\u0005\r\u0000\u0000KL\u0003\u0014\n\u0000L\u0011\u0001"+
		"\u0000\u0000\u0000MN\u0003\u0014\n\u0000NO\u0005\u000e\u0000\u0000OP\u0005"+
		"\u000f\u0000\u0000P\u0013\u0001\u0000\u0000\u0000QR\u0006\n\uffff\uffff"+
		"\u0000RT\u0005\u0010\u0000\u0000SU\u0003\u0014\n\u0000TS\u0001\u0000\u0000"+
		"\u0000TU\u0001\u0000\u0000\u0000Ux\u0001\u0000\u0000\u0000VW\u0007\u0001"+
		"\u0000\u0000Wx\u0003\u0014\n\u0013XY\u0005$\u0000\u0000Yx\u0003\u0014"+
		"\n\u000fZ[\u0005\t\u0000\u0000[\\\u0005\u0018\u0000\u0000\\]\u0003\u0014"+
		"\n\u0000]^\u0005\u0019\u0000\u0000^x\u0001\u0000\u0000\u0000_`\u0005\b"+
		"\u0000\u0000`a\u0005\u0018\u0000\u0000ab\u0003\u0014\n\u0000bc\u0005\u0019"+
		"\u0000\u0000cx\u0001\u0000\u0000\u0000de\u0005\n\u0000\u0000ef\u0005\u0018"+
		"\u0000\u0000fg\u0003\u0014\n\u0000gh\u0005\u0019\u0000\u0000hx\u0001\u0000"+
		"\u0000\u0000ij\u0005 \u0000\u0000jx\u0005$\u0000\u0000kl\u0005\'\u0000"+
		"\u0000lm\u0005\u0006\u0000\u0000mx\u0003\u0014\n\u0007nx\u0005\'\u0000"+
		"\u0000ox\u0005%\u0000\u0000px\u0005&\u0000\u0000qx\u0005$\u0000\u0000"+
		"rx\u0003\u0016\u000b\u0000st\u0005\u0018\u0000\u0000tu\u0003\u0014\n\u0000"+
		"uv\u0005\u0019\u0000\u0000vx\u0001\u0000\u0000\u0000wQ\u0001\u0000\u0000"+
		"\u0000wV\u0001\u0000\u0000\u0000wX\u0001\u0000\u0000\u0000wZ\u0001\u0000"+
		"\u0000\u0000w_\u0001\u0000\u0000\u0000wd\u0001\u0000\u0000\u0000wi\u0001"+
		"\u0000\u0000\u0000wk\u0001\u0000\u0000\u0000wn\u0001\u0000\u0000\u0000"+
		"wo\u0001\u0000\u0000\u0000wp\u0001\u0000\u0000\u0000wq\u0001\u0000\u0000"+
		"\u0000wr\u0001\u0000\u0000\u0000ws\u0001\u0000\u0000\u0000x\u008c\u0001"+
		"\u0000\u0000\u0000yz\n\u0011\u0000\u0000z{\u0007\u0002\u0000\u0000{\u008b"+
		"\u0003\u0014\n\u0012|}\n\u0010\u0000\u0000}~\u0007\u0001\u0000\u0000~"+
		"\u008b\u0003\u0014\n\u0011\u007f\u0080\n\n\u0000\u0000\u0080\u0081\u0007"+
		"\u0003\u0000\u0000\u0081\u008b\u0003\u0014\n\u000b\u0082\u0083\n\t\u0000"+
		"\u0000\u0083\u0084\u0005\u001f\u0000\u0000\u0084\u0085\u0005\"\u0000\u0000"+
		"\u0085\u008b\u0003\u0014\n\n\u0086\u0087\n\u0012\u0000\u0000\u0087\u008b"+
		"\u0005\u0013\u0000\u0000\u0088\u0089\n\u000b\u0000\u0000\u0089\u008b\u0007"+
		"\u0004\u0000\u0000\u008ay\u0001\u0000\u0000\u0000\u008a|\u0001\u0000\u0000"+
		"\u0000\u008a\u007f\u0001\u0000\u0000\u0000\u008a\u0082\u0001\u0000\u0000"+
		"\u0000\u008a\u0086\u0001\u0000\u0000\u0000\u008a\u0088\u0001\u0000\u0000"+
		"\u0000\u008b\u008e\u0001\u0000\u0000\u0000\u008c\u008a\u0001\u0000\u0000"+
		"\u0000\u008c\u008d\u0001\u0000\u0000\u0000\u008d\u0015\u0001\u0000\u0000"+
		"\u0000\u008e\u008c\u0001\u0000\u0000\u0000\u008f\u0090\u0005\u0018\u0000"+
		"\u0000\u0090\u0091\u0003\u0014\n\u0000\u0091\u0092\u0005!\u0000\u0000"+
		"\u0092\u0093\u0003\u0014\n\u0000\u0093\u0094\u0005\u0019\u0000\u0000\u0094"+
		"\u0017\u0001\u0000\u0000\u0000\b\u001b&-5Tw\u008a\u008c";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}