/**
 * 
 */
package com.senlang.sdip.model;

import java.util.Date;
import java.util.HashMap;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.senlang.sdip.service.JsonResultFactory;

/**
 * @author Mc.D
 *
 */
public abstract class BaseController<T extends Dbo> extends CommonController {

	protected abstract PagingAndSortingRepository<T, Long> getDao();

	/**
	 * 已经弃用：使用commonPgetGets(BiConsumer<PageRequest, HashMap<String, Object>>
	 * beforeFind)代替
	 * 
	 * @param pageSize
	 * @param pageNo
	 * @param beforeFind
	 * @return
	 * 
	 * @see #commonPageGets(BiConsumer)
	 */
	@Deprecated
	public HashMap<String, Object> commonPageGets(String pageSize, String pageNo,
			BiConsumer<PageRequest, HashMap<String, Object>> beforeFind) {
		if (pageSize == null) {
			pageSize = "20";
		}
		if (pageNo == null) {
			pageNo = "0";
		}
		int iPageNo = Integer.parseInt(pageNo);
		int iPageSize = Integer.parseInt(pageSize);
		PageRequest pageRequest = new PageRequest(iPageNo < 0 ? 0 : iPageNo, iPageSize < 0 ? 20 : iPageSize,
				new Sort(Direction.DESC, "id"));

		HashMap<String, Object> ret = JsonResultFactory.getOkResult();
		if (beforeFind != null) {
			beforeFind.accept(pageRequest, ret);
		}
		ret.put("data", this.getDao().findAll(pageRequest));
		return ret;
	}

	public CommonDataResult<Page<T>> commonPageGets(BiConsumer<PageRequest, CommonDataResult<Page<T>>> beforeFind) {
		PageRequest pageRequest = getPageRequest();

		// HashMap<String, Object> ret = JsonResultFactory.getOkResult();
		CommonDataResult<Page<T>> ret = JsonResultFactory.getOkDataResult(this.getDao().findAll(pageRequest));
		if (beforeFind != null) {
			beforeFind.accept(pageRequest, ret);
		}
		// ret.put("data", this.getDao().findAll(pageRequest));
		return ret;
	}

	public CommonDataResult<Iterable<T>> commonGetAll(Consumer<CommonDataResult<Iterable<T>>> afterFind) {
		// HashMap<String, Object> ret = JsonResultFactory.getOkResult();
		// ret.put("data", this.getDao().findAll());
		CommonDataResult<Iterable<T>> ret = JsonResultFactory.getOkDataResult(this.getDao().findAll());
		if (afterFind != null) {
			afterFind.accept(ret);
		}
		return ret;
	}

	public CommonDataResult<T> commonGetById(long id) {
		// HashMap<String, Object> ret = JsonResultFactory.getOkResult();
		// ret.put("data", this.getDao().findOne(id));
		return JsonResultFactory.getOkDataResult(this.getDao().findOne(id));
	}

	public CommonDataResult<T> commonAdd(T model) {
		model.createdAt = new Date();
		model.createdBy = this.CurrentUserCode;
		T t = this.getDao().save(model);
		// HashMap<String, Object> ret = JsonResultFactory.getOkResult();
		// // if (t.getClass().isInstance(Dbo.class)) {
		// // Dbo d = (Dbo) t;
		// // ret.put("data", this.getDao().findOne(d.getId()));
		// // } else {
		// // ret.put("data", t);
		// // }
		// ret.put("data", this.getDao().findOne(t.getId()));

		return JsonResultFactory.getOkDataResult(this.getDao().findOne(t.getId()));
	}

	public CommonResult commonDelete(long id) {
		this.getDao().delete(id);
		return JsonResultFactory.getOkCommonResult();
	}

	public CommonDataResult<T> commonUpdate(T model) {
		model.updatedAt = new Date();
		model.updatedBy = this.CurrentUserCode;
		T t = this.getDao().save(model);
		// HashMap<String, Object> ret = JsonResultFactory.getOkResult();
		// ret.put("data", t);

		return JsonResultFactory.getOkDataResult(t);
	}
}
