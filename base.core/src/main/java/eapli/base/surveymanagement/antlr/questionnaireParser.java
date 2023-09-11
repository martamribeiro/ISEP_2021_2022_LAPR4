// Generated from D:/Faculdade/ano 2/semestre2/LAPR4/projeto_lapr4/base.core/src/main/java/eapli/base/surveymanagement/antlr\questionnaire.g4 by ANTLR 4.10.1
package eapli.base.surveymanagement.antlr;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class questionnaireParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.10.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, LIMIT=5, DECIMALS_ALLOWED=6, SINGLE_CHOICE_WITH_INPUT=7, 
		MULTIPLE_CHOICE_WITH_INPUT=8, FREE_TEXT=9, NUMERIC=10, SINGLE_CHOICE=11, 
		MULTIPLE_CHOICE=12, SORTING_OPTION=13, SCALING_OPTION=14, MANDATORY=15, 
		OPTIONAL=16, CONDITION_DEPENDENT=17, DIGITO=18, PALAVRA=19, PARENTESIS_DIREITO=20, 
		PARENTESIS_ESQUERDO=21, VIRGULA=22, ESPACO=23, DOIS_PONTOS=24, PONTO_INTERROGACAO=25, 
		PONTO_EXCLAMACAO=26, NEWLINE=27, WS=28;
	public static final int
		RULE_alfanumerico = 0, RULE_frase = 1, RULE_title = 2, RULE_message = 3, 
		RULE_survey = 4, RULE_questionnaire_id = 5, RULE_section = 6, RULE_numeric_id = 7, 
		RULE_obligatoriness = 8, RULE_repeatability = 9, RULE_question = 10, RULE_free_text = 11, 
		RULE_numeric = 12, RULE_single_choice = 13, RULE_multiple_choice = 14, 
		RULE_single_choice_with_input = 15, RULE_multiple_choice_with_input = 16, 
		RULE_sorting_option = 17, RULE_scaling_option = 18, RULE_option = 19, 
		RULE_question_text = 20;
	private static String[] makeRuleNames() {
		return new String[] {
			"alfanumerico", "frase", "title", "message", "survey", "questionnaire_id", 
			"section", "numeric_id", "obligatoriness", "repeatability", "question", 
			"free_text", "numeric", "single_choice", "multiple_choice", "single_choice_with_input", 
			"multiple_choice_with_input", "sorting_option", "scaling_option", "option", 
			"question_text"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'Section Obligatoriness: '", "'Repeatability: Q'", "'Type: '", 
			"'Scale: '", "'select only '", "'Decimal numbers are allowed!'", "'single choice with input'", 
			"'multiple choice with input'", "'free text'", "'numeric'", "'single choice'", 
			"'multiple choice'", "'sorting option'", "'scaling option'", "'mandatory'", 
			"'optional'", "'condition dependent'", null, null, "')'", "'('", "','", 
			"' '", "':'", "'?'", "'!'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, "LIMIT", "DECIMALS_ALLOWED", "SINGLE_CHOICE_WITH_INPUT", 
			"MULTIPLE_CHOICE_WITH_INPUT", "FREE_TEXT", "NUMERIC", "SINGLE_CHOICE", 
			"MULTIPLE_CHOICE", "SORTING_OPTION", "SCALING_OPTION", "MANDATORY", "OPTIONAL", 
			"CONDITION_DEPENDENT", "DIGITO", "PALAVRA", "PARENTESIS_DIREITO", "PARENTESIS_ESQUERDO", 
			"VIRGULA", "ESPACO", "DOIS_PONTOS", "PONTO_INTERROGACAO", "PONTO_EXCLAMACAO", 
			"NEWLINE", "WS"
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
	public String getGrammarFileName() { return "questionnaire.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public questionnaireParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class AlfanumericoContext extends ParserRuleContext {
		public TerminalNode PALAVRA() { return getToken(questionnaireParser.PALAVRA, 0); }
		public TerminalNode DIGITO() { return getToken(questionnaireParser.DIGITO, 0); }
		public AlfanumericoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_alfanumerico; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof questionnaireListener ) ((questionnaireListener)listener).enterAlfanumerico(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof questionnaireListener ) ((questionnaireListener)listener).exitAlfanumerico(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof questionnaireVisitor ) return ((questionnaireVisitor<? extends T>)visitor).visitAlfanumerico(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AlfanumericoContext alfanumerico() throws RecognitionException {
		AlfanumericoContext _localctx = new AlfanumericoContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_alfanumerico);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(42);
			_la = _input.LA(1);
			if ( !(_la==DIGITO || _la==PALAVRA) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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

	public static class FraseContext extends ParserRuleContext {
		public List<TerminalNode> PALAVRA() { return getTokens(questionnaireParser.PALAVRA); }
		public TerminalNode PALAVRA(int i) {
			return getToken(questionnaireParser.PALAVRA, i);
		}
		public List<TerminalNode> ESPACO() { return getTokens(questionnaireParser.ESPACO); }
		public TerminalNode ESPACO(int i) {
			return getToken(questionnaireParser.ESPACO, i);
		}
		public List<TerminalNode> VIRGULA() { return getTokens(questionnaireParser.VIRGULA); }
		public TerminalNode VIRGULA(int i) {
			return getToken(questionnaireParser.VIRGULA, i);
		}
		public List<TerminalNode> DIGITO() { return getTokens(questionnaireParser.DIGITO); }
		public TerminalNode DIGITO(int i) {
			return getToken(questionnaireParser.DIGITO, i);
		}
		public FraseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_frase; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof questionnaireListener ) ((questionnaireListener)listener).enterFrase(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof questionnaireListener ) ((questionnaireListener)listener).exitFrase(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof questionnaireVisitor ) return ((questionnaireVisitor<? extends T>)visitor).visitFrase(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FraseContext frase() throws RecognitionException {
		FraseContext _localctx = new FraseContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_frase);
		int _la;
		try {
			setState(80);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(44);
				match(PALAVRA);
				setState(56);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==VIRGULA || _la==ESPACO) {
					{
					{
					setState(46);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==VIRGULA) {
						{
						setState(45);
						match(VIRGULA);
						}
					}

					setState(48);
					match(ESPACO);
					setState(50); 
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(49);
						_la = _input.LA(1);
						if ( !(_la==DIGITO || _la==PALAVRA) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						}
						}
						setState(52); 
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( _la==DIGITO || _la==PALAVRA );
					}
					}
					setState(58);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(60); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(59);
					match(DIGITO);
					}
					}
					setState(62); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==DIGITO );
				setState(75);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==VIRGULA || _la==ESPACO) {
					{
					{
					setState(65);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==VIRGULA) {
						{
						setState(64);
						match(VIRGULA);
						}
					}

					setState(67);
					match(ESPACO);
					setState(69); 
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(68);
						_la = _input.LA(1);
						if ( !(_la==DIGITO || _la==PALAVRA) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						}
						}
						setState(71); 
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( _la==DIGITO || _la==PALAVRA );
					}
					}
					setState(77);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(78);
				match(PALAVRA);
				setState(79);
				match(VIRGULA);
				}
				break;
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

	public static class TitleContext extends ParserRuleContext {
		public TitleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_title; }
	 
		public TitleContext() { }
		public void copyFrom(TitleContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class LengthTitleContext extends TitleContext {
		public FraseContext frase() {
			return getRuleContext(FraseContext.class,0);
		}
		public LengthTitleContext(TitleContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof questionnaireListener ) ((questionnaireListener)listener).enterLengthTitle(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof questionnaireListener ) ((questionnaireListener)listener).exitLengthTitle(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof questionnaireVisitor ) return ((questionnaireVisitor<? extends T>)visitor).visitLengthTitle(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TitleContext title() throws RecognitionException {
		TitleContext _localctx = new TitleContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_title);
		try {
			_localctx = new LengthTitleContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(82);
			frase();
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

	public static class MessageContext extends ParserRuleContext {
		public List<FraseContext> frase() {
			return getRuleContexts(FraseContext.class);
		}
		public FraseContext frase(int i) {
			return getRuleContext(FraseContext.class,i);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(questionnaireParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(questionnaireParser.NEWLINE, i);
		}
		public MessageContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_message; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof questionnaireListener ) ((questionnaireListener)listener).enterMessage(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof questionnaireListener ) ((questionnaireListener)listener).exitMessage(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof questionnaireVisitor ) return ((questionnaireVisitor<? extends T>)visitor).visitMessage(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MessageContext message() throws RecognitionException {
		MessageContext _localctx = new MessageContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_message);
		int _la;
		try {
			setState(92);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(84);
				frase();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(88); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(85);
					frase();
					setState(86);
					match(NEWLINE);
					}
					}
					setState(90); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==DIGITO || _la==PALAVRA );
				}
				break;
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

	public static class SurveyContext extends ParserRuleContext {
		public Questionnaire_idContext questionnaire_id() {
			return getRuleContext(Questionnaire_idContext.class,0);
		}
		public TerminalNode ESPACO() { return getToken(questionnaireParser.ESPACO, 0); }
		public TitleContext title() {
			return getRuleContext(TitleContext.class,0);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(questionnaireParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(questionnaireParser.NEWLINE, i);
		}
		public List<MessageContext> message() {
			return getRuleContexts(MessageContext.class);
		}
		public MessageContext message(int i) {
			return getRuleContext(MessageContext.class,i);
		}
		public List<SectionContext> section() {
			return getRuleContexts(SectionContext.class);
		}
		public SectionContext section(int i) {
			return getRuleContext(SectionContext.class,i);
		}
		public SurveyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_survey; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof questionnaireListener ) ((questionnaireListener)listener).enterSurvey(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof questionnaireListener ) ((questionnaireListener)listener).exitSurvey(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof questionnaireVisitor ) return ((questionnaireVisitor<? extends T>)visitor).visitSurvey(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SurveyContext survey() throws RecognitionException {
		SurveyContext _localctx = new SurveyContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_survey);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(94);
			questionnaire_id();
			setState(95);
			match(ESPACO);
			setState(96);
			title();
			setState(97);
			match(NEWLINE);
			setState(99);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==DIGITO || _la==PALAVRA) {
				{
				setState(98);
				message();
				}
			}

			setState(103); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(101);
					match(NEWLINE);
					setState(102);
					section();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(105); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			setState(107);
			match(NEWLINE);
			setState(108);
			match(NEWLINE);
			setState(109);
			message();
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

	public static class Questionnaire_idContext extends ParserRuleContext {
		public List<AlfanumericoContext> alfanumerico() {
			return getRuleContexts(AlfanumericoContext.class);
		}
		public AlfanumericoContext alfanumerico(int i) {
			return getRuleContext(AlfanumericoContext.class,i);
		}
		public Questionnaire_idContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_questionnaire_id; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof questionnaireListener ) ((questionnaireListener)listener).enterQuestionnaire_id(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof questionnaireListener ) ((questionnaireListener)listener).exitQuestionnaire_id(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof questionnaireVisitor ) return ((questionnaireVisitor<? extends T>)visitor).visitQuestionnaire_id(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Questionnaire_idContext questionnaire_id() throws RecognitionException {
		Questionnaire_idContext _localctx = new Questionnaire_idContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_questionnaire_id);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(112); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(111);
				alfanumerico();
				}
				}
				setState(114); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==DIGITO || _la==PALAVRA );
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

	public static class SectionContext extends ParserRuleContext {
		public Numeric_idContext numeric_id() {
			return getRuleContext(Numeric_idContext.class,0);
		}
		public TitleContext title() {
			return getRuleContext(TitleContext.class,0);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(questionnaireParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(questionnaireParser.NEWLINE, i);
		}
		public ObligatorinessContext obligatoriness() {
			return getRuleContext(ObligatorinessContext.class,0);
		}
		public MessageContext message() {
			return getRuleContext(MessageContext.class,0);
		}
		public RepeatabilityContext repeatability() {
			return getRuleContext(RepeatabilityContext.class,0);
		}
		public List<QuestionContext> question() {
			return getRuleContexts(QuestionContext.class);
		}
		public QuestionContext question(int i) {
			return getRuleContext(QuestionContext.class,i);
		}
		public SectionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_section; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof questionnaireListener ) ((questionnaireListener)listener).enterSection(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof questionnaireListener ) ((questionnaireListener)listener).exitSection(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof questionnaireVisitor ) return ((questionnaireVisitor<? extends T>)visitor).visitSection(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SectionContext section() throws RecognitionException {
		SectionContext _localctx = new SectionContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_section);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(116);
			numeric_id();
			setState(117);
			title();
			setState(118);
			match(NEWLINE);
			setState(120);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==DIGITO || _la==PALAVRA) {
				{
				setState(119);
				message();
				}
			}

			setState(122);
			match(T__0);
			setState(123);
			obligatoriness();
			setState(127);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				{
				setState(124);
				match(NEWLINE);
				setState(125);
				match(T__1);
				setState(126);
				repeatability();
				}
				break;
			}
			setState(129);
			match(NEWLINE);
			setState(131); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(130);
				question();
				}
				}
				setState(133); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==DIGITO );
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

	public static class Numeric_idContext extends ParserRuleContext {
		public List<TerminalNode> DIGITO() { return getTokens(questionnaireParser.DIGITO); }
		public TerminalNode DIGITO(int i) {
			return getToken(questionnaireParser.DIGITO, i);
		}
		public Numeric_idContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numeric_id; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof questionnaireListener ) ((questionnaireListener)listener).enterNumeric_id(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof questionnaireListener ) ((questionnaireListener)listener).exitNumeric_id(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof questionnaireVisitor ) return ((questionnaireVisitor<? extends T>)visitor).visitNumeric_id(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Numeric_idContext numeric_id() throws RecognitionException {
		Numeric_idContext _localctx = new Numeric_idContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_numeric_id);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(136); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(135);
					match(DIGITO);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(138); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
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

	public static class ObligatorinessContext extends ParserRuleContext {
		public TerminalNode MANDATORY() { return getToken(questionnaireParser.MANDATORY, 0); }
		public TerminalNode OPTIONAL() { return getToken(questionnaireParser.OPTIONAL, 0); }
		public TerminalNode CONDITION_DEPENDENT() { return getToken(questionnaireParser.CONDITION_DEPENDENT, 0); }
		public TerminalNode DOIS_PONTOS() { return getToken(questionnaireParser.DOIS_PONTOS, 0); }
		public TerminalNode ESPACO() { return getToken(questionnaireParser.ESPACO, 0); }
		public FraseContext frase() {
			return getRuleContext(FraseContext.class,0);
		}
		public ObligatorinessContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_obligatoriness; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof questionnaireListener ) ((questionnaireListener)listener).enterObligatoriness(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof questionnaireListener ) ((questionnaireListener)listener).exitObligatoriness(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof questionnaireVisitor ) return ((questionnaireVisitor<? extends T>)visitor).visitObligatoriness(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ObligatorinessContext obligatoriness() throws RecognitionException {
		ObligatorinessContext _localctx = new ObligatorinessContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_obligatoriness);
		try {
			setState(146);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case MANDATORY:
				enterOuterAlt(_localctx, 1);
				{
				setState(140);
				match(MANDATORY);
				}
				break;
			case OPTIONAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(141);
				match(OPTIONAL);
				}
				break;
			case CONDITION_DEPENDENT:
				enterOuterAlt(_localctx, 3);
				{
				setState(142);
				match(CONDITION_DEPENDENT);
				setState(143);
				match(DOIS_PONTOS);
				setState(144);
				match(ESPACO);
				setState(145);
				frase();
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class RepeatabilityContext extends ParserRuleContext {
		public List<TerminalNode> DIGITO() { return getTokens(questionnaireParser.DIGITO); }
		public TerminalNode DIGITO(int i) {
			return getToken(questionnaireParser.DIGITO, i);
		}
		public RepeatabilityContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_repeatability; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof questionnaireListener ) ((questionnaireListener)listener).enterRepeatability(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof questionnaireListener ) ((questionnaireListener)listener).exitRepeatability(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof questionnaireVisitor ) return ((questionnaireVisitor<? extends T>)visitor).visitRepeatability(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RepeatabilityContext repeatability() throws RecognitionException {
		RepeatabilityContext _localctx = new RepeatabilityContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_repeatability);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(149); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(148);
				match(DIGITO);
				}
				}
				setState(151); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==DIGITO );
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

	public static class QuestionContext extends ParserRuleContext {
		public Free_textContext free_text() {
			return getRuleContext(Free_textContext.class,0);
		}
		public NumericContext numeric() {
			return getRuleContext(NumericContext.class,0);
		}
		public Single_choiceContext single_choice() {
			return getRuleContext(Single_choiceContext.class,0);
		}
		public Multiple_choiceContext multiple_choice() {
			return getRuleContext(Multiple_choiceContext.class,0);
		}
		public Single_choice_with_inputContext single_choice_with_input() {
			return getRuleContext(Single_choice_with_inputContext.class,0);
		}
		public Multiple_choice_with_inputContext multiple_choice_with_input() {
			return getRuleContext(Multiple_choice_with_inputContext.class,0);
		}
		public Sorting_optionContext sorting_option() {
			return getRuleContext(Sorting_optionContext.class,0);
		}
		public Scaling_optionContext scaling_option() {
			return getRuleContext(Scaling_optionContext.class,0);
		}
		public QuestionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_question; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof questionnaireListener ) ((questionnaireListener)listener).enterQuestion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof questionnaireListener ) ((questionnaireListener)listener).exitQuestion(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof questionnaireVisitor ) return ((questionnaireVisitor<? extends T>)visitor).visitQuestion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QuestionContext question() throws RecognitionException {
		QuestionContext _localctx = new QuestionContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_question);
		try {
			setState(161);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(153);
				free_text();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(154);
				numeric();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(155);
				single_choice();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(156);
				multiple_choice();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(157);
				single_choice_with_input();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(158);
				multiple_choice_with_input();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(159);
				sorting_option();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(160);
				scaling_option();
				}
				break;
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

	public static class Free_textContext extends ParserRuleContext {
		public Numeric_idContext numeric_id() {
			return getRuleContext(Numeric_idContext.class,0);
		}
		public Question_textContext question_text() {
			return getRuleContext(Question_textContext.class,0);
		}
		public TerminalNode PARENTESIS_ESQUERDO() { return getToken(questionnaireParser.PARENTESIS_ESQUERDO, 0); }
		public ObligatorinessContext obligatoriness() {
			return getRuleContext(ObligatorinessContext.class,0);
		}
		public TerminalNode PARENTESIS_DIREITO() { return getToken(questionnaireParser.PARENTESIS_DIREITO, 0); }
		public List<TerminalNode> NEWLINE() { return getTokens(questionnaireParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(questionnaireParser.NEWLINE, i);
		}
		public TerminalNode FREE_TEXT() { return getToken(questionnaireParser.FREE_TEXT, 0); }
		public List<MessageContext> message() {
			return getRuleContexts(MessageContext.class);
		}
		public MessageContext message(int i) {
			return getRuleContext(MessageContext.class,i);
		}
		public Free_textContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_free_text; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof questionnaireListener ) ((questionnaireListener)listener).enterFree_text(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof questionnaireListener ) ((questionnaireListener)listener).exitFree_text(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof questionnaireVisitor ) return ((questionnaireVisitor<? extends T>)visitor).visitFree_text(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Free_textContext free_text() throws RecognitionException {
		Free_textContext _localctx = new Free_textContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_free_text);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(163);
			numeric_id();
			setState(164);
			question_text();
			setState(165);
			match(PARENTESIS_ESQUERDO);
			setState(166);
			obligatoriness();
			setState(167);
			match(PARENTESIS_DIREITO);
			setState(170);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
			case 1:
				{
				setState(168);
				match(NEWLINE);
				setState(169);
				message();
				}
				break;
			}
			setState(172);
			match(NEWLINE);
			setState(173);
			match(T__2);
			setState(174);
			match(FREE_TEXT);
			setState(175);
			match(NEWLINE);
			setState(176);
			match(NEWLINE);
			setState(180);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				{
				setState(177);
				message();
				setState(178);
				match(NEWLINE);
				}
				break;
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

	public static class NumericContext extends ParserRuleContext {
		public Numeric_idContext numeric_id() {
			return getRuleContext(Numeric_idContext.class,0);
		}
		public Question_textContext question_text() {
			return getRuleContext(Question_textContext.class,0);
		}
		public List<TerminalNode> PARENTESIS_ESQUERDO() { return getTokens(questionnaireParser.PARENTESIS_ESQUERDO); }
		public TerminalNode PARENTESIS_ESQUERDO(int i) {
			return getToken(questionnaireParser.PARENTESIS_ESQUERDO, i);
		}
		public ObligatorinessContext obligatoriness() {
			return getRuleContext(ObligatorinessContext.class,0);
		}
		public List<TerminalNode> PARENTESIS_DIREITO() { return getTokens(questionnaireParser.PARENTESIS_DIREITO); }
		public TerminalNode PARENTESIS_DIREITO(int i) {
			return getToken(questionnaireParser.PARENTESIS_DIREITO, i);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(questionnaireParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(questionnaireParser.NEWLINE, i);
		}
		public TerminalNode NUMERIC() { return getToken(questionnaireParser.NUMERIC, 0); }
		public List<MessageContext> message() {
			return getRuleContexts(MessageContext.class);
		}
		public MessageContext message(int i) {
			return getRuleContext(MessageContext.class,i);
		}
		public TerminalNode ESPACO() { return getToken(questionnaireParser.ESPACO, 0); }
		public TerminalNode DECIMALS_ALLOWED() { return getToken(questionnaireParser.DECIMALS_ALLOWED, 0); }
		public NumericContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numeric; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof questionnaireListener ) ((questionnaireListener)listener).enterNumeric(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof questionnaireListener ) ((questionnaireListener)listener).exitNumeric(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof questionnaireVisitor ) return ((questionnaireVisitor<? extends T>)visitor).visitNumeric(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumericContext numeric() throws RecognitionException {
		NumericContext _localctx = new NumericContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_numeric);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(182);
			numeric_id();
			setState(183);
			question_text();
			setState(184);
			match(PARENTESIS_ESQUERDO);
			setState(185);
			obligatoriness();
			setState(186);
			match(PARENTESIS_DIREITO);
			setState(189);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
			case 1:
				{
				setState(187);
				match(NEWLINE);
				setState(188);
				message();
				}
				break;
			}
			setState(191);
			match(NEWLINE);
			setState(192);
			match(T__2);
			setState(193);
			match(NUMERIC);
			setState(198);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ESPACO) {
				{
				setState(194);
				match(ESPACO);
				setState(195);
				match(PARENTESIS_ESQUERDO);
				setState(196);
				match(DECIMALS_ALLOWED);
				setState(197);
				match(PARENTESIS_DIREITO);
				}
			}

			setState(200);
			match(NEWLINE);
			setState(201);
			match(NEWLINE);
			setState(205);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
			case 1:
				{
				setState(202);
				message();
				setState(203);
				match(NEWLINE);
				}
				break;
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

	public static class Single_choiceContext extends ParserRuleContext {
		public Numeric_idContext numeric_id() {
			return getRuleContext(Numeric_idContext.class,0);
		}
		public Question_textContext question_text() {
			return getRuleContext(Question_textContext.class,0);
		}
		public TerminalNode PARENTESIS_ESQUERDO() { return getToken(questionnaireParser.PARENTESIS_ESQUERDO, 0); }
		public ObligatorinessContext obligatoriness() {
			return getRuleContext(ObligatorinessContext.class,0);
		}
		public TerminalNode PARENTESIS_DIREITO() { return getToken(questionnaireParser.PARENTESIS_DIREITO, 0); }
		public List<TerminalNode> NEWLINE() { return getTokens(questionnaireParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(questionnaireParser.NEWLINE, i);
		}
		public TerminalNode SINGLE_CHOICE() { return getToken(questionnaireParser.SINGLE_CHOICE, 0); }
		public List<MessageContext> message() {
			return getRuleContexts(MessageContext.class);
		}
		public MessageContext message(int i) {
			return getRuleContext(MessageContext.class,i);
		}
		public List<OptionContext> option() {
			return getRuleContexts(OptionContext.class);
		}
		public OptionContext option(int i) {
			return getRuleContext(OptionContext.class,i);
		}
		public Single_choiceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_single_choice; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof questionnaireListener ) ((questionnaireListener)listener).enterSingle_choice(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof questionnaireListener ) ((questionnaireListener)listener).exitSingle_choice(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof questionnaireVisitor ) return ((questionnaireVisitor<? extends T>)visitor).visitSingle_choice(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Single_choiceContext single_choice() throws RecognitionException {
		Single_choiceContext _localctx = new Single_choiceContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_single_choice);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(207);
			numeric_id();
			setState(208);
			question_text();
			setState(209);
			match(PARENTESIS_ESQUERDO);
			setState(210);
			obligatoriness();
			setState(211);
			match(PARENTESIS_DIREITO);
			setState(214);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,25,_ctx) ) {
			case 1:
				{
				setState(212);
				match(NEWLINE);
				setState(213);
				message();
				}
				break;
			}
			setState(216);
			match(NEWLINE);
			setState(217);
			match(T__2);
			setState(218);
			match(SINGLE_CHOICE);
			setState(219);
			match(NEWLINE);
			setState(221); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(220);
				option();
				}
				}
				setState(223); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==DIGITO );
			setState(225);
			match(NEWLINE);
			setState(229);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,27,_ctx) ) {
			case 1:
				{
				setState(226);
				message();
				setState(227);
				match(NEWLINE);
				}
				break;
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

	public static class Multiple_choiceContext extends ParserRuleContext {
		public Numeric_idContext numeric_id() {
			return getRuleContext(Numeric_idContext.class,0);
		}
		public Question_textContext question_text() {
			return getRuleContext(Question_textContext.class,0);
		}
		public List<TerminalNode> PARENTESIS_ESQUERDO() { return getTokens(questionnaireParser.PARENTESIS_ESQUERDO); }
		public TerminalNode PARENTESIS_ESQUERDO(int i) {
			return getToken(questionnaireParser.PARENTESIS_ESQUERDO, i);
		}
		public ObligatorinessContext obligatoriness() {
			return getRuleContext(ObligatorinessContext.class,0);
		}
		public List<TerminalNode> PARENTESIS_DIREITO() { return getTokens(questionnaireParser.PARENTESIS_DIREITO); }
		public TerminalNode PARENTESIS_DIREITO(int i) {
			return getToken(questionnaireParser.PARENTESIS_DIREITO, i);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(questionnaireParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(questionnaireParser.NEWLINE, i);
		}
		public TerminalNode MULTIPLE_CHOICE() { return getToken(questionnaireParser.MULTIPLE_CHOICE, 0); }
		public List<MessageContext> message() {
			return getRuleContexts(MessageContext.class);
		}
		public MessageContext message(int i) {
			return getRuleContext(MessageContext.class,i);
		}
		public TerminalNode ESPACO() { return getToken(questionnaireParser.ESPACO, 0); }
		public TerminalNode LIMIT() { return getToken(questionnaireParser.LIMIT, 0); }
		public TerminalNode DIGITO() { return getToken(questionnaireParser.DIGITO, 0); }
		public List<OptionContext> option() {
			return getRuleContexts(OptionContext.class);
		}
		public OptionContext option(int i) {
			return getRuleContext(OptionContext.class,i);
		}
		public Multiple_choiceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multiple_choice; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof questionnaireListener ) ((questionnaireListener)listener).enterMultiple_choice(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof questionnaireListener ) ((questionnaireListener)listener).exitMultiple_choice(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof questionnaireVisitor ) return ((questionnaireVisitor<? extends T>)visitor).visitMultiple_choice(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Multiple_choiceContext multiple_choice() throws RecognitionException {
		Multiple_choiceContext _localctx = new Multiple_choiceContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_multiple_choice);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(231);
			numeric_id();
			setState(232);
			question_text();
			setState(233);
			match(PARENTESIS_ESQUERDO);
			setState(234);
			obligatoriness();
			setState(235);
			match(PARENTESIS_DIREITO);
			setState(238);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,28,_ctx) ) {
			case 1:
				{
				setState(236);
				match(NEWLINE);
				setState(237);
				message();
				}
				break;
			}
			setState(240);
			match(NEWLINE);
			setState(241);
			match(T__2);
			setState(242);
			match(MULTIPLE_CHOICE);
			setState(248);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ESPACO) {
				{
				setState(243);
				match(ESPACO);
				setState(244);
				match(PARENTESIS_ESQUERDO);
				setState(245);
				match(LIMIT);
				setState(246);
				match(DIGITO);
				setState(247);
				match(PARENTESIS_DIREITO);
				}
			}

			setState(250);
			match(NEWLINE);
			setState(252); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(251);
				option();
				}
				}
				setState(254); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==DIGITO );
			setState(256);
			match(NEWLINE);
			setState(260);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,31,_ctx) ) {
			case 1:
				{
				setState(257);
				message();
				setState(258);
				match(NEWLINE);
				}
				break;
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

	public static class Single_choice_with_inputContext extends ParserRuleContext {
		public Numeric_idContext numeric_id() {
			return getRuleContext(Numeric_idContext.class,0);
		}
		public Question_textContext question_text() {
			return getRuleContext(Question_textContext.class,0);
		}
		public TerminalNode PARENTESIS_ESQUERDO() { return getToken(questionnaireParser.PARENTESIS_ESQUERDO, 0); }
		public ObligatorinessContext obligatoriness() {
			return getRuleContext(ObligatorinessContext.class,0);
		}
		public TerminalNode PARENTESIS_DIREITO() { return getToken(questionnaireParser.PARENTESIS_DIREITO, 0); }
		public List<TerminalNode> NEWLINE() { return getTokens(questionnaireParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(questionnaireParser.NEWLINE, i);
		}
		public TerminalNode SINGLE_CHOICE_WITH_INPUT() { return getToken(questionnaireParser.SINGLE_CHOICE_WITH_INPUT, 0); }
		public List<MessageContext> message() {
			return getRuleContexts(MessageContext.class);
		}
		public MessageContext message(int i) {
			return getRuleContext(MessageContext.class,i);
		}
		public List<OptionContext> option() {
			return getRuleContexts(OptionContext.class);
		}
		public OptionContext option(int i) {
			return getRuleContext(OptionContext.class,i);
		}
		public Single_choice_with_inputContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_single_choice_with_input; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof questionnaireListener ) ((questionnaireListener)listener).enterSingle_choice_with_input(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof questionnaireListener ) ((questionnaireListener)listener).exitSingle_choice_with_input(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof questionnaireVisitor ) return ((questionnaireVisitor<? extends T>)visitor).visitSingle_choice_with_input(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Single_choice_with_inputContext single_choice_with_input() throws RecognitionException {
		Single_choice_with_inputContext _localctx = new Single_choice_with_inputContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_single_choice_with_input);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(262);
			numeric_id();
			setState(263);
			question_text();
			setState(264);
			match(PARENTESIS_ESQUERDO);
			setState(265);
			obligatoriness();
			setState(266);
			match(PARENTESIS_DIREITO);
			setState(269);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,32,_ctx) ) {
			case 1:
				{
				setState(267);
				match(NEWLINE);
				setState(268);
				message();
				}
				break;
			}
			setState(271);
			match(NEWLINE);
			setState(272);
			match(T__2);
			setState(273);
			match(SINGLE_CHOICE_WITH_INPUT);
			setState(274);
			match(NEWLINE);
			setState(276); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(275);
				option();
				}
				}
				setState(278); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==DIGITO );
			setState(280);
			match(NEWLINE);
			setState(284);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,34,_ctx) ) {
			case 1:
				{
				setState(281);
				message();
				setState(282);
				match(NEWLINE);
				}
				break;
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

	public static class Multiple_choice_with_inputContext extends ParserRuleContext {
		public Numeric_idContext numeric_id() {
			return getRuleContext(Numeric_idContext.class,0);
		}
		public Question_textContext question_text() {
			return getRuleContext(Question_textContext.class,0);
		}
		public TerminalNode PARENTESIS_ESQUERDO() { return getToken(questionnaireParser.PARENTESIS_ESQUERDO, 0); }
		public ObligatorinessContext obligatoriness() {
			return getRuleContext(ObligatorinessContext.class,0);
		}
		public TerminalNode PARENTESIS_DIREITO() { return getToken(questionnaireParser.PARENTESIS_DIREITO, 0); }
		public List<TerminalNode> NEWLINE() { return getTokens(questionnaireParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(questionnaireParser.NEWLINE, i);
		}
		public TerminalNode MULTIPLE_CHOICE_WITH_INPUT() { return getToken(questionnaireParser.MULTIPLE_CHOICE_WITH_INPUT, 0); }
		public List<MessageContext> message() {
			return getRuleContexts(MessageContext.class);
		}
		public MessageContext message(int i) {
			return getRuleContext(MessageContext.class,i);
		}
		public List<OptionContext> option() {
			return getRuleContexts(OptionContext.class);
		}
		public OptionContext option(int i) {
			return getRuleContext(OptionContext.class,i);
		}
		public Multiple_choice_with_inputContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multiple_choice_with_input; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof questionnaireListener ) ((questionnaireListener)listener).enterMultiple_choice_with_input(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof questionnaireListener ) ((questionnaireListener)listener).exitMultiple_choice_with_input(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof questionnaireVisitor ) return ((questionnaireVisitor<? extends T>)visitor).visitMultiple_choice_with_input(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Multiple_choice_with_inputContext multiple_choice_with_input() throws RecognitionException {
		Multiple_choice_with_inputContext _localctx = new Multiple_choice_with_inputContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_multiple_choice_with_input);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(286);
			numeric_id();
			setState(287);
			question_text();
			setState(288);
			match(PARENTESIS_ESQUERDO);
			setState(289);
			obligatoriness();
			setState(290);
			match(PARENTESIS_DIREITO);
			setState(293);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,35,_ctx) ) {
			case 1:
				{
				setState(291);
				match(NEWLINE);
				setState(292);
				message();
				}
				break;
			}
			setState(295);
			match(NEWLINE);
			setState(296);
			match(T__2);
			setState(297);
			match(MULTIPLE_CHOICE_WITH_INPUT);
			setState(298);
			match(NEWLINE);
			setState(300); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(299);
				option();
				}
				}
				setState(302); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==DIGITO );
			setState(304);
			match(NEWLINE);
			setState(308);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,37,_ctx) ) {
			case 1:
				{
				setState(305);
				message();
				setState(306);
				match(NEWLINE);
				}
				break;
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

	public static class Sorting_optionContext extends ParserRuleContext {
		public Numeric_idContext numeric_id() {
			return getRuleContext(Numeric_idContext.class,0);
		}
		public Question_textContext question_text() {
			return getRuleContext(Question_textContext.class,0);
		}
		public TerminalNode PARENTESIS_ESQUERDO() { return getToken(questionnaireParser.PARENTESIS_ESQUERDO, 0); }
		public ObligatorinessContext obligatoriness() {
			return getRuleContext(ObligatorinessContext.class,0);
		}
		public TerminalNode PARENTESIS_DIREITO() { return getToken(questionnaireParser.PARENTESIS_DIREITO, 0); }
		public List<TerminalNode> NEWLINE() { return getTokens(questionnaireParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(questionnaireParser.NEWLINE, i);
		}
		public TerminalNode SORTING_OPTION() { return getToken(questionnaireParser.SORTING_OPTION, 0); }
		public List<MessageContext> message() {
			return getRuleContexts(MessageContext.class);
		}
		public MessageContext message(int i) {
			return getRuleContext(MessageContext.class,i);
		}
		public List<OptionContext> option() {
			return getRuleContexts(OptionContext.class);
		}
		public OptionContext option(int i) {
			return getRuleContext(OptionContext.class,i);
		}
		public Sorting_optionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sorting_option; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof questionnaireListener ) ((questionnaireListener)listener).enterSorting_option(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof questionnaireListener ) ((questionnaireListener)listener).exitSorting_option(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof questionnaireVisitor ) return ((questionnaireVisitor<? extends T>)visitor).visitSorting_option(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Sorting_optionContext sorting_option() throws RecognitionException {
		Sorting_optionContext _localctx = new Sorting_optionContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_sorting_option);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(310);
			numeric_id();
			setState(311);
			question_text();
			setState(312);
			match(PARENTESIS_ESQUERDO);
			setState(313);
			obligatoriness();
			setState(314);
			match(PARENTESIS_DIREITO);
			setState(317);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,38,_ctx) ) {
			case 1:
				{
				setState(315);
				match(NEWLINE);
				setState(316);
				message();
				}
				break;
			}
			setState(319);
			match(NEWLINE);
			setState(320);
			match(T__2);
			setState(321);
			match(SORTING_OPTION);
			setState(322);
			match(NEWLINE);
			setState(324); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(323);
				option();
				}
				}
				setState(326); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==DIGITO );
			setState(328);
			match(NEWLINE);
			setState(332);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,40,_ctx) ) {
			case 1:
				{
				setState(329);
				message();
				setState(330);
				match(NEWLINE);
				}
				break;
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

	public static class Scaling_optionContext extends ParserRuleContext {
		public Numeric_idContext numeric_id() {
			return getRuleContext(Numeric_idContext.class,0);
		}
		public Question_textContext question_text() {
			return getRuleContext(Question_textContext.class,0);
		}
		public TerminalNode PARENTESIS_ESQUERDO() { return getToken(questionnaireParser.PARENTESIS_ESQUERDO, 0); }
		public ObligatorinessContext obligatoriness() {
			return getRuleContext(ObligatorinessContext.class,0);
		}
		public TerminalNode PARENTESIS_DIREITO() { return getToken(questionnaireParser.PARENTESIS_DIREITO, 0); }
		public List<TerminalNode> NEWLINE() { return getTokens(questionnaireParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(questionnaireParser.NEWLINE, i);
		}
		public TerminalNode SCALING_OPTION() { return getToken(questionnaireParser.SCALING_OPTION, 0); }
		public FraseContext frase() {
			return getRuleContext(FraseContext.class,0);
		}
		public List<MessageContext> message() {
			return getRuleContexts(MessageContext.class);
		}
		public MessageContext message(int i) {
			return getRuleContext(MessageContext.class,i);
		}
		public List<OptionContext> option() {
			return getRuleContexts(OptionContext.class);
		}
		public OptionContext option(int i) {
			return getRuleContext(OptionContext.class,i);
		}
		public Scaling_optionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_scaling_option; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof questionnaireListener ) ((questionnaireListener)listener).enterScaling_option(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof questionnaireListener ) ((questionnaireListener)listener).exitScaling_option(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof questionnaireVisitor ) return ((questionnaireVisitor<? extends T>)visitor).visitScaling_option(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Scaling_optionContext scaling_option() throws RecognitionException {
		Scaling_optionContext _localctx = new Scaling_optionContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_scaling_option);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(334);
			numeric_id();
			setState(335);
			question_text();
			setState(336);
			match(PARENTESIS_ESQUERDO);
			setState(337);
			obligatoriness();
			setState(338);
			match(PARENTESIS_DIREITO);
			setState(341);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,41,_ctx) ) {
			case 1:
				{
				setState(339);
				match(NEWLINE);
				setState(340);
				message();
				}
				break;
			}
			setState(343);
			match(NEWLINE);
			setState(344);
			match(T__2);
			setState(345);
			match(SCALING_OPTION);
			setState(346);
			match(NEWLINE);
			setState(347);
			match(T__3);
			setState(348);
			frase();
			setState(349);
			match(NEWLINE);
			setState(351); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(350);
				option();
				}
				}
				setState(353); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==DIGITO );
			setState(355);
			match(NEWLINE);
			setState(359);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,43,_ctx) ) {
			case 1:
				{
				setState(356);
				message();
				setState(357);
				match(NEWLINE);
				}
				break;
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

	public static class OptionContext extends ParserRuleContext {
		public Numeric_idContext numeric_id() {
			return getRuleContext(Numeric_idContext.class,0);
		}
		public TerminalNode PARENTESIS_DIREITO() { return getToken(questionnaireParser.PARENTESIS_DIREITO, 0); }
		public FraseContext frase() {
			return getRuleContext(FraseContext.class,0);
		}
		public TerminalNode NEWLINE() { return getToken(questionnaireParser.NEWLINE, 0); }
		public TerminalNode DOIS_PONTOS() { return getToken(questionnaireParser.DOIS_PONTOS, 0); }
		public OptionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_option; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof questionnaireListener ) ((questionnaireListener)listener).enterOption(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof questionnaireListener ) ((questionnaireListener)listener).exitOption(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof questionnaireVisitor ) return ((questionnaireVisitor<? extends T>)visitor).visitOption(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OptionContext option() throws RecognitionException {
		OptionContext _localctx = new OptionContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_option);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(361);
			numeric_id();
			setState(362);
			match(PARENTESIS_DIREITO);
			setState(363);
			frase();
			setState(365);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==DOIS_PONTOS) {
				{
				setState(364);
				match(DOIS_PONTOS);
				}
			}

			setState(367);
			match(NEWLINE);
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

	public static class Question_textContext extends ParserRuleContext {
		public FraseContext frase() {
			return getRuleContext(FraseContext.class,0);
		}
		public TerminalNode PONTO_INTERROGACAO() { return getToken(questionnaireParser.PONTO_INTERROGACAO, 0); }
		public Question_textContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_question_text; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof questionnaireListener ) ((questionnaireListener)listener).enterQuestion_text(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof questionnaireListener ) ((questionnaireListener)listener).exitQuestion_text(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof questionnaireVisitor ) return ((questionnaireVisitor<? extends T>)visitor).visitQuestion_text(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Question_textContext question_text() throws RecognitionException {
		Question_textContext _localctx = new Question_textContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_question_text);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(369);
			frase();
			setState(370);
			match(PONTO_INTERROGACAO);
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

	public static final String _serializedATN =
		"\u0004\u0001\u001c\u0175\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001"+
		"\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004"+
		"\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007"+
		"\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b"+
		"\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007"+
		"\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007"+
		"\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0001\u0000\u0001"+
		"\u0000\u0001\u0001\u0001\u0001\u0003\u0001/\b\u0001\u0001\u0001\u0001"+
		"\u0001\u0004\u00013\b\u0001\u000b\u0001\f\u00014\u0005\u00017\b\u0001"+
		"\n\u0001\f\u0001:\t\u0001\u0001\u0001\u0004\u0001=\b\u0001\u000b\u0001"+
		"\f\u0001>\u0001\u0001\u0003\u0001B\b\u0001\u0001\u0001\u0001\u0001\u0004"+
		"\u0001F\b\u0001\u000b\u0001\f\u0001G\u0005\u0001J\b\u0001\n\u0001\f\u0001"+
		"M\t\u0001\u0001\u0001\u0001\u0001\u0003\u0001Q\b\u0001\u0001\u0002\u0001"+
		"\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0004\u0003Y\b"+
		"\u0003\u000b\u0003\f\u0003Z\u0003\u0003]\b\u0003\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0003\u0004d\b\u0004\u0001\u0004"+
		"\u0001\u0004\u0004\u0004h\b\u0004\u000b\u0004\f\u0004i\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0005\u0004\u0005q\b\u0005\u000b"+
		"\u0005\f\u0005r\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0003"+
		"\u0006y\b\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0003\u0006\u0080\b\u0006\u0001\u0006\u0001\u0006\u0004\u0006\u0084"+
		"\b\u0006\u000b\u0006\f\u0006\u0085\u0001\u0007\u0004\u0007\u0089\b\u0007"+
		"\u000b\u0007\f\u0007\u008a\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0003\b\u0093\b\b\u0001\t\u0004\t\u0096\b\t\u000b\t\f\t\u0097\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0003\n\u00a2"+
		"\b\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0003\u000b\u00ab\b\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0003"+
		"\u000b\u00b5\b\u000b\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0003\f\u00be\b\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0003\f\u00c7\b\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0003\f\u00ce"+
		"\b\f\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0003\r\u00d7"+
		"\b\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0004\r\u00de\b\r\u000b\r"+
		"\f\r\u00df\u0001\r\u0001\r\u0001\r\u0001\r\u0003\r\u00e6\b\r\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0003\u000e\u00ef\b\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0003\u000e\u00f9\b\u000e"+
		"\u0001\u000e\u0001\u000e\u0004\u000e\u00fd\b\u000e\u000b\u000e\f\u000e"+
		"\u00fe\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0003\u000e\u0105"+
		"\b\u000e\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001"+
		"\u000f\u0001\u000f\u0003\u000f\u010e\b\u000f\u0001\u000f\u0001\u000f\u0001"+
		"\u000f\u0001\u000f\u0001\u000f\u0004\u000f\u0115\b\u000f\u000b\u000f\f"+
		"\u000f\u0116\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0003\u000f"+
		"\u011d\b\u000f\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0003\u0010\u0126\b\u0010\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0004\u0010\u012d\b\u0010\u000b\u0010"+
		"\f\u0010\u012e\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0003\u0010"+
		"\u0135\b\u0010\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011"+
		"\u0001\u0011\u0001\u0011\u0003\u0011\u013e\b\u0011\u0001\u0011\u0001\u0011"+
		"\u0001\u0011\u0001\u0011\u0001\u0011\u0004\u0011\u0145\b\u0011\u000b\u0011"+
		"\f\u0011\u0146\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0003\u0011"+
		"\u014d\b\u0011\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012"+
		"\u0001\u0012\u0001\u0012\u0003\u0012\u0156\b\u0012\u0001\u0012\u0001\u0012"+
		"\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012"+
		"\u0004\u0012\u0160\b\u0012\u000b\u0012\f\u0012\u0161\u0001\u0012\u0001"+
		"\u0012\u0001\u0012\u0001\u0012\u0003\u0012\u0168\b\u0012\u0001\u0013\u0001"+
		"\u0013\u0001\u0013\u0001\u0013\u0003\u0013\u016e\b\u0013\u0001\u0013\u0001"+
		"\u0013\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0000\u0000\u0015"+
		"\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a"+
		"\u001c\u001e \"$&(\u0000\u0001\u0001\u0000\u0012\u0013\u0194\u0000*\u0001"+
		"\u0000\u0000\u0000\u0002P\u0001\u0000\u0000\u0000\u0004R\u0001\u0000\u0000"+
		"\u0000\u0006\\\u0001\u0000\u0000\u0000\b^\u0001\u0000\u0000\u0000\np\u0001"+
		"\u0000\u0000\u0000\ft\u0001\u0000\u0000\u0000\u000e\u0088\u0001\u0000"+
		"\u0000\u0000\u0010\u0092\u0001\u0000\u0000\u0000\u0012\u0095\u0001\u0000"+
		"\u0000\u0000\u0014\u00a1\u0001\u0000\u0000\u0000\u0016\u00a3\u0001\u0000"+
		"\u0000\u0000\u0018\u00b6\u0001\u0000\u0000\u0000\u001a\u00cf\u0001\u0000"+
		"\u0000\u0000\u001c\u00e7\u0001\u0000\u0000\u0000\u001e\u0106\u0001\u0000"+
		"\u0000\u0000 \u011e\u0001\u0000\u0000\u0000\"\u0136\u0001\u0000\u0000"+
		"\u0000$\u014e\u0001\u0000\u0000\u0000&\u0169\u0001\u0000\u0000\u0000("+
		"\u0171\u0001\u0000\u0000\u0000*+\u0007\u0000\u0000\u0000+\u0001\u0001"+
		"\u0000\u0000\u0000,8\u0005\u0013\u0000\u0000-/\u0005\u0016\u0000\u0000"+
		".-\u0001\u0000\u0000\u0000./\u0001\u0000\u0000\u0000/0\u0001\u0000\u0000"+
		"\u000002\u0005\u0017\u0000\u000013\u0007\u0000\u0000\u000021\u0001\u0000"+
		"\u0000\u000034\u0001\u0000\u0000\u000042\u0001\u0000\u0000\u000045\u0001"+
		"\u0000\u0000\u000057\u0001\u0000\u0000\u00006.\u0001\u0000\u0000\u0000"+
		"7:\u0001\u0000\u0000\u000086\u0001\u0000\u0000\u000089\u0001\u0000\u0000"+
		"\u00009Q\u0001\u0000\u0000\u0000:8\u0001\u0000\u0000\u0000;=\u0005\u0012"+
		"\u0000\u0000<;\u0001\u0000\u0000\u0000=>\u0001\u0000\u0000\u0000><\u0001"+
		"\u0000\u0000\u0000>?\u0001\u0000\u0000\u0000?K\u0001\u0000\u0000\u0000"+
		"@B\u0005\u0016\u0000\u0000A@\u0001\u0000\u0000\u0000AB\u0001\u0000\u0000"+
		"\u0000BC\u0001\u0000\u0000\u0000CE\u0005\u0017\u0000\u0000DF\u0007\u0000"+
		"\u0000\u0000ED\u0001\u0000\u0000\u0000FG\u0001\u0000\u0000\u0000GE\u0001"+
		"\u0000\u0000\u0000GH\u0001\u0000\u0000\u0000HJ\u0001\u0000\u0000\u0000"+
		"IA\u0001\u0000\u0000\u0000JM\u0001\u0000\u0000\u0000KI\u0001\u0000\u0000"+
		"\u0000KL\u0001\u0000\u0000\u0000LQ\u0001\u0000\u0000\u0000MK\u0001\u0000"+
		"\u0000\u0000NO\u0005\u0013\u0000\u0000OQ\u0005\u0016\u0000\u0000P,\u0001"+
		"\u0000\u0000\u0000P<\u0001\u0000\u0000\u0000PN\u0001\u0000\u0000\u0000"+
		"Q\u0003\u0001\u0000\u0000\u0000RS\u0003\u0002\u0001\u0000S\u0005\u0001"+
		"\u0000\u0000\u0000T]\u0003\u0002\u0001\u0000UV\u0003\u0002\u0001\u0000"+
		"VW\u0005\u001b\u0000\u0000WY\u0001\u0000\u0000\u0000XU\u0001\u0000\u0000"+
		"\u0000YZ\u0001\u0000\u0000\u0000ZX\u0001\u0000\u0000\u0000Z[\u0001\u0000"+
		"\u0000\u0000[]\u0001\u0000\u0000\u0000\\T\u0001\u0000\u0000\u0000\\X\u0001"+
		"\u0000\u0000\u0000]\u0007\u0001\u0000\u0000\u0000^_\u0003\n\u0005\u0000"+
		"_`\u0005\u0017\u0000\u0000`a\u0003\u0004\u0002\u0000ac\u0005\u001b\u0000"+
		"\u0000bd\u0003\u0006\u0003\u0000cb\u0001\u0000\u0000\u0000cd\u0001\u0000"+
		"\u0000\u0000dg\u0001\u0000\u0000\u0000ef\u0005\u001b\u0000\u0000fh\u0003"+
		"\f\u0006\u0000ge\u0001\u0000\u0000\u0000hi\u0001\u0000\u0000\u0000ig\u0001"+
		"\u0000\u0000\u0000ij\u0001\u0000\u0000\u0000jk\u0001\u0000\u0000\u0000"+
		"kl\u0005\u001b\u0000\u0000lm\u0005\u001b\u0000\u0000mn\u0003\u0006\u0003"+
		"\u0000n\t\u0001\u0000\u0000\u0000oq\u0003\u0000\u0000\u0000po\u0001\u0000"+
		"\u0000\u0000qr\u0001\u0000\u0000\u0000rp\u0001\u0000\u0000\u0000rs\u0001"+
		"\u0000\u0000\u0000s\u000b\u0001\u0000\u0000\u0000tu\u0003\u000e\u0007"+
		"\u0000uv\u0003\u0004\u0002\u0000vx\u0005\u001b\u0000\u0000wy\u0003\u0006"+
		"\u0003\u0000xw\u0001\u0000\u0000\u0000xy\u0001\u0000\u0000\u0000yz\u0001"+
		"\u0000\u0000\u0000z{\u0005\u0001\u0000\u0000{\u007f\u0003\u0010\b\u0000"+
		"|}\u0005\u001b\u0000\u0000}~\u0005\u0002\u0000\u0000~\u0080\u0003\u0012"+
		"\t\u0000\u007f|\u0001\u0000\u0000\u0000\u007f\u0080\u0001\u0000\u0000"+
		"\u0000\u0080\u0081\u0001\u0000\u0000\u0000\u0081\u0083\u0005\u001b\u0000"+
		"\u0000\u0082\u0084\u0003\u0014\n\u0000\u0083\u0082\u0001\u0000\u0000\u0000"+
		"\u0084\u0085\u0001\u0000\u0000\u0000\u0085\u0083\u0001\u0000\u0000\u0000"+
		"\u0085\u0086\u0001\u0000\u0000\u0000\u0086\r\u0001\u0000\u0000\u0000\u0087"+
		"\u0089\u0005\u0012\u0000\u0000\u0088\u0087\u0001\u0000\u0000\u0000\u0089"+
		"\u008a\u0001\u0000\u0000\u0000\u008a\u0088\u0001\u0000\u0000\u0000\u008a"+
		"\u008b\u0001\u0000\u0000\u0000\u008b\u000f\u0001\u0000\u0000\u0000\u008c"+
		"\u0093\u0005\u000f\u0000\u0000\u008d\u0093\u0005\u0010\u0000\u0000\u008e"+
		"\u008f\u0005\u0011\u0000\u0000\u008f\u0090\u0005\u0018\u0000\u0000\u0090"+
		"\u0091\u0005\u0017\u0000\u0000\u0091\u0093\u0003\u0002\u0001\u0000\u0092"+
		"\u008c\u0001\u0000\u0000\u0000\u0092\u008d\u0001\u0000\u0000\u0000\u0092"+
		"\u008e\u0001\u0000\u0000\u0000\u0093\u0011\u0001\u0000\u0000\u0000\u0094"+
		"\u0096\u0005\u0012\u0000\u0000\u0095\u0094\u0001\u0000\u0000\u0000\u0096"+
		"\u0097\u0001\u0000\u0000\u0000\u0097\u0095\u0001\u0000\u0000\u0000\u0097"+
		"\u0098\u0001\u0000\u0000\u0000\u0098\u0013\u0001\u0000\u0000\u0000\u0099"+
		"\u00a2\u0003\u0016\u000b\u0000\u009a\u00a2\u0003\u0018\f\u0000\u009b\u00a2"+
		"\u0003\u001a\r\u0000\u009c\u00a2\u0003\u001c\u000e\u0000\u009d\u00a2\u0003"+
		"\u001e\u000f\u0000\u009e\u00a2\u0003 \u0010\u0000\u009f\u00a2\u0003\""+
		"\u0011\u0000\u00a0\u00a2\u0003$\u0012\u0000\u00a1\u0099\u0001\u0000\u0000"+
		"\u0000\u00a1\u009a\u0001\u0000\u0000\u0000\u00a1\u009b\u0001\u0000\u0000"+
		"\u0000\u00a1\u009c\u0001\u0000\u0000\u0000\u00a1\u009d\u0001\u0000\u0000"+
		"\u0000\u00a1\u009e\u0001\u0000\u0000\u0000\u00a1\u009f\u0001\u0000\u0000"+
		"\u0000\u00a1\u00a0\u0001\u0000\u0000\u0000\u00a2\u0015\u0001\u0000\u0000"+
		"\u0000\u00a3\u00a4\u0003\u000e\u0007\u0000\u00a4\u00a5\u0003(\u0014\u0000"+
		"\u00a5\u00a6\u0005\u0015\u0000\u0000\u00a6\u00a7\u0003\u0010\b\u0000\u00a7"+
		"\u00aa\u0005\u0014\u0000\u0000\u00a8\u00a9\u0005\u001b\u0000\u0000\u00a9"+
		"\u00ab\u0003\u0006\u0003\u0000\u00aa\u00a8\u0001\u0000\u0000\u0000\u00aa"+
		"\u00ab\u0001\u0000\u0000\u0000\u00ab\u00ac\u0001\u0000\u0000\u0000\u00ac"+
		"\u00ad\u0005\u001b\u0000\u0000\u00ad\u00ae\u0005\u0003\u0000\u0000\u00ae"+
		"\u00af\u0005\t\u0000\u0000\u00af\u00b0\u0005\u001b\u0000\u0000\u00b0\u00b4"+
		"\u0005\u001b\u0000\u0000\u00b1\u00b2\u0003\u0006\u0003\u0000\u00b2\u00b3"+
		"\u0005\u001b\u0000\u0000\u00b3\u00b5\u0001\u0000\u0000\u0000\u00b4\u00b1"+
		"\u0001\u0000\u0000\u0000\u00b4\u00b5\u0001\u0000\u0000\u0000\u00b5\u0017"+
		"\u0001\u0000\u0000\u0000\u00b6\u00b7\u0003\u000e\u0007\u0000\u00b7\u00b8"+
		"\u0003(\u0014\u0000\u00b8\u00b9\u0005\u0015\u0000\u0000\u00b9\u00ba\u0003"+
		"\u0010\b\u0000\u00ba\u00bd\u0005\u0014\u0000\u0000\u00bb\u00bc\u0005\u001b"+
		"\u0000\u0000\u00bc\u00be\u0003\u0006\u0003\u0000\u00bd\u00bb\u0001\u0000"+
		"\u0000\u0000\u00bd\u00be\u0001\u0000\u0000\u0000\u00be\u00bf\u0001\u0000"+
		"\u0000\u0000\u00bf\u00c0\u0005\u001b\u0000\u0000\u00c0\u00c1\u0005\u0003"+
		"\u0000\u0000\u00c1\u00c6\u0005\n\u0000\u0000\u00c2\u00c3\u0005\u0017\u0000"+
		"\u0000\u00c3\u00c4\u0005\u0015\u0000\u0000\u00c4\u00c5\u0005\u0006\u0000"+
		"\u0000\u00c5\u00c7\u0005\u0014\u0000\u0000\u00c6\u00c2\u0001\u0000\u0000"+
		"\u0000\u00c6\u00c7\u0001\u0000\u0000\u0000\u00c7\u00c8\u0001\u0000\u0000"+
		"\u0000\u00c8\u00c9\u0005\u001b\u0000\u0000\u00c9\u00cd\u0005\u001b\u0000"+
		"\u0000\u00ca\u00cb\u0003\u0006\u0003\u0000\u00cb\u00cc\u0005\u001b\u0000"+
		"\u0000\u00cc\u00ce\u0001\u0000\u0000\u0000\u00cd\u00ca\u0001\u0000\u0000"+
		"\u0000\u00cd\u00ce\u0001\u0000\u0000\u0000\u00ce\u0019\u0001\u0000\u0000"+
		"\u0000\u00cf\u00d0\u0003\u000e\u0007\u0000\u00d0\u00d1\u0003(\u0014\u0000"+
		"\u00d1\u00d2\u0005\u0015\u0000\u0000\u00d2\u00d3\u0003\u0010\b\u0000\u00d3"+
		"\u00d6\u0005\u0014\u0000\u0000\u00d4\u00d5\u0005\u001b\u0000\u0000\u00d5"+
		"\u00d7\u0003\u0006\u0003\u0000\u00d6\u00d4\u0001\u0000\u0000\u0000\u00d6"+
		"\u00d7\u0001\u0000\u0000\u0000\u00d7\u00d8\u0001\u0000\u0000\u0000\u00d8"+
		"\u00d9\u0005\u001b\u0000\u0000\u00d9\u00da\u0005\u0003\u0000\u0000\u00da"+
		"\u00db\u0005\u000b\u0000\u0000\u00db\u00dd\u0005\u001b\u0000\u0000\u00dc"+
		"\u00de\u0003&\u0013\u0000\u00dd\u00dc\u0001\u0000\u0000\u0000\u00de\u00df"+
		"\u0001\u0000\u0000\u0000\u00df\u00dd\u0001\u0000\u0000\u0000\u00df\u00e0"+
		"\u0001\u0000\u0000\u0000\u00e0\u00e1\u0001\u0000\u0000\u0000\u00e1\u00e5"+
		"\u0005\u001b\u0000\u0000\u00e2\u00e3\u0003\u0006\u0003\u0000\u00e3\u00e4"+
		"\u0005\u001b\u0000\u0000\u00e4\u00e6\u0001\u0000\u0000\u0000\u00e5\u00e2"+
		"\u0001\u0000\u0000\u0000\u00e5\u00e6\u0001\u0000\u0000\u0000\u00e6\u001b"+
		"\u0001\u0000\u0000\u0000\u00e7\u00e8\u0003\u000e\u0007\u0000\u00e8\u00e9"+
		"\u0003(\u0014\u0000\u00e9\u00ea\u0005\u0015\u0000\u0000\u00ea\u00eb\u0003"+
		"\u0010\b\u0000\u00eb\u00ee\u0005\u0014\u0000\u0000\u00ec\u00ed\u0005\u001b"+
		"\u0000\u0000\u00ed\u00ef\u0003\u0006\u0003\u0000\u00ee\u00ec\u0001\u0000"+
		"\u0000\u0000\u00ee\u00ef\u0001\u0000\u0000\u0000\u00ef\u00f0\u0001\u0000"+
		"\u0000\u0000\u00f0\u00f1\u0005\u001b\u0000\u0000\u00f1\u00f2\u0005\u0003"+
		"\u0000\u0000\u00f2\u00f8\u0005\f\u0000\u0000\u00f3\u00f4\u0005\u0017\u0000"+
		"\u0000\u00f4\u00f5\u0005\u0015\u0000\u0000\u00f5\u00f6\u0005\u0005\u0000"+
		"\u0000\u00f6\u00f7\u0005\u0012\u0000\u0000\u00f7\u00f9\u0005\u0014\u0000"+
		"\u0000\u00f8\u00f3\u0001\u0000\u0000\u0000\u00f8\u00f9\u0001\u0000\u0000"+
		"\u0000\u00f9\u00fa\u0001\u0000\u0000\u0000\u00fa\u00fc\u0005\u001b\u0000"+
		"\u0000\u00fb\u00fd\u0003&\u0013\u0000\u00fc\u00fb\u0001\u0000\u0000\u0000"+
		"\u00fd\u00fe\u0001\u0000\u0000\u0000\u00fe\u00fc\u0001\u0000\u0000\u0000"+
		"\u00fe\u00ff\u0001\u0000\u0000\u0000\u00ff\u0100\u0001\u0000\u0000\u0000"+
		"\u0100\u0104\u0005\u001b\u0000\u0000\u0101\u0102\u0003\u0006\u0003\u0000"+
		"\u0102\u0103\u0005\u001b\u0000\u0000\u0103\u0105\u0001\u0000\u0000\u0000"+
		"\u0104\u0101\u0001\u0000\u0000\u0000\u0104\u0105\u0001\u0000\u0000\u0000"+
		"\u0105\u001d\u0001\u0000\u0000\u0000\u0106\u0107\u0003\u000e\u0007\u0000"+
		"\u0107\u0108\u0003(\u0014\u0000\u0108\u0109\u0005\u0015\u0000\u0000\u0109"+
		"\u010a\u0003\u0010\b\u0000\u010a\u010d\u0005\u0014\u0000\u0000\u010b\u010c"+
		"\u0005\u001b\u0000\u0000\u010c\u010e\u0003\u0006\u0003\u0000\u010d\u010b"+
		"\u0001\u0000\u0000\u0000\u010d\u010e\u0001\u0000\u0000\u0000\u010e\u010f"+
		"\u0001\u0000\u0000\u0000\u010f\u0110\u0005\u001b\u0000\u0000\u0110\u0111"+
		"\u0005\u0003\u0000\u0000\u0111\u0112\u0005\u0007\u0000\u0000\u0112\u0114"+
		"\u0005\u001b\u0000\u0000\u0113\u0115\u0003&\u0013\u0000\u0114\u0113\u0001"+
		"\u0000\u0000\u0000\u0115\u0116\u0001\u0000\u0000\u0000\u0116\u0114\u0001"+
		"\u0000\u0000\u0000\u0116\u0117\u0001\u0000\u0000\u0000\u0117\u0118\u0001"+
		"\u0000\u0000\u0000\u0118\u011c\u0005\u001b\u0000\u0000\u0119\u011a\u0003"+
		"\u0006\u0003\u0000\u011a\u011b\u0005\u001b\u0000\u0000\u011b\u011d\u0001"+
		"\u0000\u0000\u0000\u011c\u0119\u0001\u0000\u0000\u0000\u011c\u011d\u0001"+
		"\u0000\u0000\u0000\u011d\u001f\u0001\u0000\u0000\u0000\u011e\u011f\u0003"+
		"\u000e\u0007\u0000\u011f\u0120\u0003(\u0014\u0000\u0120\u0121\u0005\u0015"+
		"\u0000\u0000\u0121\u0122\u0003\u0010\b\u0000\u0122\u0125\u0005\u0014\u0000"+
		"\u0000\u0123\u0124\u0005\u001b\u0000\u0000\u0124\u0126\u0003\u0006\u0003"+
		"\u0000\u0125\u0123\u0001\u0000\u0000\u0000\u0125\u0126\u0001\u0000\u0000"+
		"\u0000\u0126\u0127\u0001\u0000\u0000\u0000\u0127\u0128\u0005\u001b\u0000"+
		"\u0000\u0128\u0129\u0005\u0003\u0000\u0000\u0129\u012a\u0005\b\u0000\u0000"+
		"\u012a\u012c\u0005\u001b\u0000\u0000\u012b\u012d\u0003&\u0013\u0000\u012c"+
		"\u012b\u0001\u0000\u0000\u0000\u012d\u012e\u0001\u0000\u0000\u0000\u012e"+
		"\u012c\u0001\u0000\u0000\u0000\u012e\u012f\u0001\u0000\u0000\u0000\u012f"+
		"\u0130\u0001\u0000\u0000\u0000\u0130\u0134\u0005\u001b\u0000\u0000\u0131"+
		"\u0132\u0003\u0006\u0003\u0000\u0132\u0133\u0005\u001b\u0000\u0000\u0133"+
		"\u0135\u0001\u0000\u0000\u0000\u0134\u0131\u0001\u0000\u0000\u0000\u0134"+
		"\u0135\u0001\u0000\u0000\u0000\u0135!\u0001\u0000\u0000\u0000\u0136\u0137"+
		"\u0003\u000e\u0007\u0000\u0137\u0138\u0003(\u0014\u0000\u0138\u0139\u0005"+
		"\u0015\u0000\u0000\u0139\u013a\u0003\u0010\b\u0000\u013a\u013d\u0005\u0014"+
		"\u0000\u0000\u013b\u013c\u0005\u001b\u0000\u0000\u013c\u013e\u0003\u0006"+
		"\u0003\u0000\u013d\u013b\u0001\u0000\u0000\u0000\u013d\u013e\u0001\u0000"+
		"\u0000\u0000\u013e\u013f\u0001\u0000\u0000\u0000\u013f\u0140\u0005\u001b"+
		"\u0000\u0000\u0140\u0141\u0005\u0003\u0000\u0000\u0141\u0142\u0005\r\u0000"+
		"\u0000\u0142\u0144\u0005\u001b\u0000\u0000\u0143\u0145\u0003&\u0013\u0000"+
		"\u0144\u0143\u0001\u0000\u0000\u0000\u0145\u0146\u0001\u0000\u0000\u0000"+
		"\u0146\u0144\u0001\u0000\u0000\u0000\u0146\u0147\u0001\u0000\u0000\u0000"+
		"\u0147\u0148\u0001\u0000\u0000\u0000\u0148\u014c\u0005\u001b\u0000\u0000"+
		"\u0149\u014a\u0003\u0006\u0003\u0000\u014a\u014b\u0005\u001b\u0000\u0000"+
		"\u014b\u014d\u0001\u0000\u0000\u0000\u014c\u0149\u0001\u0000\u0000\u0000"+
		"\u014c\u014d\u0001\u0000\u0000\u0000\u014d#\u0001\u0000\u0000\u0000\u014e"+
		"\u014f\u0003\u000e\u0007\u0000\u014f\u0150\u0003(\u0014\u0000\u0150\u0151"+
		"\u0005\u0015\u0000\u0000\u0151\u0152\u0003\u0010\b\u0000\u0152\u0155\u0005"+
		"\u0014\u0000\u0000\u0153\u0154\u0005\u001b\u0000\u0000\u0154\u0156\u0003"+
		"\u0006\u0003\u0000\u0155\u0153\u0001\u0000\u0000\u0000\u0155\u0156\u0001"+
		"\u0000\u0000\u0000\u0156\u0157\u0001\u0000\u0000\u0000\u0157\u0158\u0005"+
		"\u001b\u0000\u0000\u0158\u0159\u0005\u0003\u0000\u0000\u0159\u015a\u0005"+
		"\u000e\u0000\u0000\u015a\u015b\u0005\u001b\u0000\u0000\u015b\u015c\u0005"+
		"\u0004\u0000\u0000\u015c\u015d\u0003\u0002\u0001\u0000\u015d\u015f\u0005"+
		"\u001b\u0000\u0000\u015e\u0160\u0003&\u0013\u0000\u015f\u015e\u0001\u0000"+
		"\u0000\u0000\u0160\u0161\u0001\u0000\u0000\u0000\u0161\u015f\u0001\u0000"+
		"\u0000\u0000\u0161\u0162\u0001\u0000\u0000\u0000\u0162\u0163\u0001\u0000"+
		"\u0000\u0000\u0163\u0167\u0005\u001b\u0000\u0000\u0164\u0165\u0003\u0006"+
		"\u0003\u0000\u0165\u0166\u0005\u001b\u0000\u0000\u0166\u0168\u0001\u0000"+
		"\u0000\u0000\u0167\u0164\u0001\u0000\u0000\u0000\u0167\u0168\u0001\u0000"+
		"\u0000\u0000\u0168%\u0001\u0000\u0000\u0000\u0169\u016a\u0003\u000e\u0007"+
		"\u0000\u016a\u016b\u0005\u0014\u0000\u0000\u016b\u016d\u0003\u0002\u0001"+
		"\u0000\u016c\u016e\u0005\u0018\u0000\u0000\u016d\u016c\u0001\u0000\u0000"+
		"\u0000\u016d\u016e\u0001\u0000\u0000\u0000\u016e\u016f\u0001\u0000\u0000"+
		"\u0000\u016f\u0170\u0005\u001b\u0000\u0000\u0170\'\u0001\u0000\u0000\u0000"+
		"\u0171\u0172\u0003\u0002\u0001\u0000\u0172\u0173\u0005\u0019\u0000\u0000"+
		"\u0173)\u0001\u0000\u0000\u0000-.48>AGKPZ\\cirx\u007f\u0085\u008a\u0092"+
		"\u0097\u00a1\u00aa\u00b4\u00bd\u00c6\u00cd\u00d6\u00df\u00e5\u00ee\u00f8"+
		"\u00fe\u0104\u010d\u0116\u011c\u0125\u012e\u0134\u013d\u0146\u014c\u0155"+
		"\u0161\u0167\u016d";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}