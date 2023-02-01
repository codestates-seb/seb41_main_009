import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import styled from 'styled-components';
import { LabelLarge } from '../../styles/typo';

/**
 * 버튼 클릭으로 currentTab state 를 변경하는 lnb 컴포넌트
 * @param {number} currentTab - 현재 탭의 인덱스
 * @param {function} handleTab - 탭 state 변경 함수
 * @returns
 */
const Lnb = ({ currentTab, category = '' }) => {
  const navigate = useNavigate();

  // const tabList = [{ title: 'All' }, { title: 'Posts' }, { title: 'Series' }];
  const tabList = [{ title: 'Posts' }, { title: 'Series' }];

  const handleTab = tab => {
    navigate(`/${tab}/${category}`);
  };

  return (
    <Container>
      <LeftButtonList>
        {tabList.map(el =>
          currentTab === el.title ? (
            <TabButton key={el.title} clicked onClick={() => handleTab(el.title)}>
              {el.title}
            </TabButton>
          ) : (
            <TabButton key={el.title} onClick={() => handleTab(el.title)}>
              {el.title}
            </TabButton>
          ),
        )}
      </LeftButtonList>
      <Filter>Filter</Filter>
    </Container>
  );
};

const LnbSearch = ({ value }) => {
  const navigate = useNavigate();

  const [nowTab, SetNowTab] = useState();

  // const tabList = [{ title: 'All' }, { title: 'Posts' }, { title: 'Series' }];
  const tabList = [{ title: 'All' }, { title: 'Posts' }, { title: 'Series' }];

  const handleTab = tab => {
    SetNowTab(tab);
    navigate(`?value=${value}&type=${tab}`);
  };

  return (
    <Container>
      <LeftButtonList>
        {tabList.map(el =>
          nowTab === el.title ? (
            <TabButton key={el.title} clicked onClick={() => handleTab(el.title)}>
              {el.title}
            </TabButton>
          ) : (
            <TabButton key={el.title} onClick={() => handleTab(el.title)}>
              {el.title}
            </TabButton>
          ),
        )}
      </LeftButtonList>
      <Filter>Filter</Filter>
    </Container>
  );
};

const Container = styled.div`
  ${LabelLarge};
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 100%;
  margin: 15px 91px;
  height: auto;
`;

const LeftButtonList = styled.div`
  display: flex;
  gap: 35px;
`;

const Filter = styled.button`
  all: unset;
  color: var(--gray-900);
  cursor: pointer;
`;

const TabButton = styled.button`
  all: unset;
  color: ${props => (props.clicked ? 'var(--gray-900)' : 'var(--gray-500)')};
  cursor: pointer;
`;

export { Lnb, LnbSearch };
