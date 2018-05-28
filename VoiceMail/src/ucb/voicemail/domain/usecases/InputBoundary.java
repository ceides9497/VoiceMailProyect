package ucb.voicemail.domain.usecases;

public interface InputBoundary<RequestModel, ResponseModel> {
	public ResponseModel handle(RequestModel request);
}
