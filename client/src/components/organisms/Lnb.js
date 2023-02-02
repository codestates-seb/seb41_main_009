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

  const tabList = [
    { title: 'Posts', id: 'posts' },
    { title: 'Series', id: 'series' },
  ];
  const handleTab = tab => {
    navigate(`/${tab}/${category}`);
  };

  return (
    <Container>
      <LeftButtonList>
        {tabList.map(el =>
          currentTab === el.title ? (
            <TabButton key={el.title} clicked onClick={() => handleTab(el.id)}>
              {el.title}
            </TabButton>
          ) : (
            <TabButton key={el.title} onClick={() => handleTab(el.id)}>
              {el.title}
            </TabButton>
          ),
        )}
      </LeftButtonList>
    </Container>
  );
};

const LnbSearch = ({ query }) => {
  const navigate = useNavigate();

  const [nowTab, SetNowTab] = useState();

  // const tabList = [{ title: 'All' }, { title: 'Posts' }, { title: 'Series' }];
  const tabList = [
    { title: 'All', id: `all` },
    { title: 'Posts', id: 'posts' },
    { title: 'Series', id: 'series' },
  ];

  const handleTab = tab => {
    SetNowTab(tab);
    navigate(`/search/${tab}?query=${query}&page=1&size=10`);
  };

  return (
    <Container>
      <LeftButtonList>
        {tabList.map(el =>
          nowTab === el.title ? (
            <TabButton key={el.title} clicked onClick={() => handleTab(el.id)}>
              {el.title}
            </TabButton>
          ) : (
            <TabButton key={el.title} onClick={() => handleTab(el.id)}>
              {el.title}
            </TabButton>
          ),
        )}
      </LeftButtonList>
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

const TabButton = styled.button`
  all: unset;
  color: ${props => (props.clicked ? 'var(--orange-400)' : 'var(--gray-500)')};
  cursor: pointer;
`;

export { Lnb, LnbSearch };
