
public aspect TraceAspectKaewvichien {
	pointcut classToTrace(): within(ComponentApp) || within(DataApp) || within(ServiceApp);
	
	pointcut methodToTrace(): classToTrace() && execution(* *(..));
	
	before(): methodToTrace() {
		String info = thisJoinPointStaticPart.getSignature() + ", "
		+ thisJoinPointStaticPart.getSourceLocation().getFileName() + ", "
		+ thisJoinPointStaticPart.getSourceLocation().getLine();
		System.out.println(info);
	}
	after(): methodToTrace() {
		System.out.println(thisJoinPointStaticPart.getSignature());
	}

}
