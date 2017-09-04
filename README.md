# webmethods-integrationserver-java8-stream-filtering
Examples on howto use Java 8 Streaming

# Usage
1. Create a Java method in Designer and specify "indocs" as a doctype list as input param and "outdocs" as a doctype list as output params.
2. Copy the code of filterSample into the method.
3. Adjust the code according to your needs.

# Code Explanation

	public static final void filterSample(IData pipeline) throws ServiceException {
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
		
		// indocs
		IData[]	indocs = IDataUtil.getIDataArray( pipelineCursor, "indocs" );
			
		pipelineCursor.destroy();
		
		java.util.List<IData> idataList = (java.util.List) Arrays.asList(indocs);
		
		java.util.List<IData> result = idataList.stream()
			.filter( idata -> idata.toString().indexOf("street=ABCStreet 42") > -1)
			.collect(Collectors.toList());
		
		result.stream()
			.forEach(System.out::println);
		
		// pipeline
		IDataCursor pipelineCursor_1 = pipeline.getCursor();
		
		// outdocs
		IDataUtil.put( pipelineCursor_1, "outdocs", result.toArray() );
		pipelineCursor_1.destroy();
			
	}
	
The line "java.util.List<IData> idataList = (java.util.List) Arrays.asList(indocs);" prepares the input idata list for Java 8 streaming.

The .filter operation filters out each input doc from the list, which is not matching the criteria "field street must match ACBStreet 42".

All input street fields matching the criteria are add to the result set (result) with the collect operation.

The result.stream().forEach(system.out::println) is optional.


