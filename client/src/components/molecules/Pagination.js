/* eslint-disable no-plusplus */
import { useEffect, useMemo, useState } from 'react';
import styled from 'styled-components';
import { useSearchParams } from 'react-router-dom';

const PageButton = styled.button`
  margin-right: 5px;
  padding: 7px 10px;
  line-height: 0.9;
  border: 2px solid var(--gray-900);
  box-shadow: var(--boxShadow-00) black;
  &.focus {
    border: 2px solid var(--gray-700);
    background-color: var(--gray-700);
    color: white;
    box-shadow: var(--boxShadow-00) var(--gray-700);

    &:hover {
      background-color: var(--gray-500);
    }
  }

  &:hover {
    background-color: var(--gray-500);
  }
`;

const Pagination = ({ totalPages }) => {
  const range = (start, end) => {
    const array = [];
    for (let i = start; i < end; ++i) {
      array.push(i);
    }
    return array;
  };

  const [curPage, setCurPage] = useState(1);
  const pageArr = useMemo(() => {
    // p
    if (totalPages <= 5) {
      return range(2, totalPages);
    }
    if (curPage >= totalPages - 2) {
      return range(totalPages - 4, totalPages);
    }
    if (curPage >= 5) {
      return range(curPage - 2, curPage + 3);
    }
    return range(2, 6);
  }, [curPage, totalPages]);
  const [searchParams, setSearchParams] = useSearchParams();

  useEffect(() => {
    if (searchParams.has('page')) {
      setCurPage(Number(searchParams.get('page')));
    } else {
      setCurPage(1);
    }
  }, [searchParams]);

  // const handleClick = (e) => {};

  return (
    <div>
      {curPage === 1 ? (
        ''
      ) : (
        <PageButton
          onClick={() => {
            setCurPage(curPage - 1);
            searchParams.set('page', curPage - 1);
            setSearchParams(searchParams);
          }}>
          prev
        </PageButton>
      )}

      <PageButton
        className={curPage === 1 ? 'focus' : ''}
        onClick={() => {
          setCurPage(1);
          searchParams.set('page', 1);
          setSearchParams(searchParams);
        }}>
        1
      </PageButton>

      {curPage < 5 || totalPages <= 5 ? '' : <PageButton>...</PageButton>}
      {pageArr.map(el => {
        return (
          <PageButton
            key={el}
            value={el}
            className={el === curPage ? 'focus' : ''}
            onClick={() => {
              setCurPage(el);
              searchParams.set('page', el);
              setSearchParams(searchParams);
            }}>
            {el}
          </PageButton>
        );
      })}
      {curPage >= totalPages - 2 || totalPages <= 5 ? '' : <PageButton>...</PageButton>}

      <PageButton
        className={`${curPage === totalPages ? 'focus' : ''} ${totalPages <= 1 ? 'hidden' : ''}`}
        onClick={() => {
          setCurPage(totalPages);
          searchParams.set('page', totalPages);
          setSearchParams(searchParams);
        }}>
        {totalPages}
      </PageButton>

      {curPage === totalPages || totalPages <= 1 ? (
        ''
      ) : (
        <PageButton
          onClick={() => {
            setCurPage(curPage + 1);
            searchParams.set('page', curPage + 1);
            setSearchParams(searchParams);
          }}>
          next
        </PageButton>
      )}
    </div>
  );
};

export default Pagination;
