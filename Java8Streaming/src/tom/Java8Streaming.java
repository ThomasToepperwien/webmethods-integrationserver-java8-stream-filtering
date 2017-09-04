package tom;

import java.util.Arrays;
import java.util.stream.Collectors;

import com.wm.app.b2b.client.ServiceException;
import com.wm.data.IData;
import com.wm.data.IDataCursor;
import com.wm.data.IDataUtil;

public class Java8Streaming {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
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

}
