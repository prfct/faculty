package com.my.faculty.context;

import com.my.faculty.controller.ControllerContext;
import com.my.faculty.persistance.dao.DaoContext;
import com.my.faculty.persistance.dao.DaoFactory;
import com.my.faculty.persistance.dao.DaoFactoryImpl;
import com.my.faculty.service.ServiceContext;
import com.my.faculty.service.ServiceFactory;
import com.my.faculty.service.ServiceFactoryImpl;

/**
 * @author Oleksii Petrokhalko.
 */
public class ApplicationContext {
    private static ApplicationContext instance;
    private ServiceContext serviceContext;
    private DaoContext daoContext;
    private ControllerContext controllerContext;

    private ApplicationContext() {
    }

    private static synchronized ApplicationContext getInstance() {
        if (instance == null) {
            instance = new ApplicationContext();
            instance.initDao();
            instance.initService();
            instance.initControllers();
        }
        return instance;
    }

    private void initDao() {
        DaoFactory daoFactory = new DaoFactoryImpl();
        daoContext = DaoContext.init(daoFactory);
    }

    private void initService() {
        ServiceFactory serviceFactory = new ServiceFactoryImpl();
        serviceContext = ServiceContext.init(serviceFactory);
    }

    private void initControllers() {
        controllerContext = ControllerContext.init();
    }

    public static ServiceContext getServiceContext() {
        return ApplicationContext.getInstance().serviceContext;
    }

    public static DaoContext getDaoContext() {
        return ApplicationContext.getInstance().daoContext;
    }

    public static ControllerContext getControllerContext() {
        return ApplicationContext.getInstance().controllerContext;
    }
}
