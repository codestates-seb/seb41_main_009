import axios from 'axios';
import { useState, useEffect } from 'react';
import { seriesListDummy } from '../constants/dummyData';

/**
 *
 * @param {string} category
 * @param {string} page
 * @returns {seriesList[], seriesPageInfo{}, boolean, boolean}
 */

// 내용 수정 필요
const useGetSeriesListbyUser = ({ writer }) => {
  const [seriesList, setSeriesList] = useState([]);
  const [seriesPageInfo, setSeriesPageInfo] = useState({});
  const [isLoading, setIsLoading] = useState(false);
  const [isLoadingError, setIsLoadingError] = useState(false);

  const URL = `/member/${writer}`;

  useEffect(() => {
    setIsLoading(true);

    axios
      .get(URL)
      .then(({ data, pageInfo }) => {
        setSeriesList(data);
        setSeriesPageInfo(pageInfo);
        setIsLoading(false);
      })
      .catch(err => {
        console.log(err);
        setIsLoading(false);
        setIsLoadingError(true);
      })
      .finally(() => {
        const { data, pageInfo } = seriesListDummy;
        setSeriesList(data);
        setSeriesPageInfo(pageInfo);
        setIsLoading(false);
        setIsLoadingError(false);
      });
  }, []);

  // 아직은 seriesList에 seriesListDummy의 값을 넣음
  return { seriesList, seriesPageInfo, isLoading, isLoadingError };
};

export default useGetSeriesListbyUser;
